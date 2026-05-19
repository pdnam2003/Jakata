package com.example.lanchat.event;

import com.example.lanchat.model.ChatMessage;
import com.example.lanchat.model.MessageType;
import com.example.lanchat.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        String username = chatService.removeSession(sessionId);
        if (username != null) {
            logger.info("User disconnected: {}", username);
            ChatMessage leaveMessage = new ChatMessage(MessageType.LEAVE, username + " đã rời phòng chat.", username, null);
            messagingTemplate.convertAndSend("/topic/public", leaveMessage);
            messagingTemplate.convertAndSend("/topic/users", chatService.getActiveUsers());
        }
    }
}
