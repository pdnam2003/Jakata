package com.example.lanchat.controller;

import com.example.lanchat.model.ChatMessage;
import com.example.lanchat.model.MessageType;
import com.example.lanchat.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ChatController(ChatService chatService, SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "username", required = true) String username, Model model) throws JsonProcessingException {
        List<ChatMessage> history = chatService.loadRecentMessages(username, 30);
        model.addAttribute("username", username);
        model.addAttribute("historyJson", objectMapper.writeValueAsString(history));
        return "chat";
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
        String sessionId = headerAccessor.getSessionId();
        String username = principal != null ? principal.getName() : chatMessage.getSender();
        if (username == null || username.isBlank()) {
            username = chatMessage.getSender();
        }
        headerAccessor.getSessionAttributes().put("username", username);
        chatService.registerSession(sessionId, username);

        ChatMessage joinMessage = new ChatMessage(MessageType.JOIN,
                username + " đã tham gia phòng chat.", username, null);
        messagingTemplate.convertAndSend("/topic/public", joinMessage);
        messagingTemplate.convertAndSend("/topic/users", chatService.getActiveUsers());
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage, Principal principal) {
        String sender = principal != null ? principal.getName() : chatMessage.getSender();
        if (sender == null || sender.isBlank()) {
            sender = chatMessage.getSender();
        }
        chatMessage.setSender(sender);

        if (chatMessage.getType() == MessageType.TYPING) {
            messagingTemplate.convertAndSend("/topic/typing", chatMessage);
            return;
        }

        if (chatMessage.getRecipient() != null && !chatMessage.getRecipient().isBlank()) {
            chatMessage.setType(MessageType.PRIVATE);
            chatService.saveMessage(chatMessage);
            messagingTemplate.convertAndSendToUser(chatMessage.getRecipient(), "/queue/private", chatMessage);
            messagingTemplate.convertAndSendToUser(sender, "/queue/private", chatMessage);
        } else {
            chatMessage.setType(MessageType.CHAT);
            chatService.saveMessage(chatMessage);
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
