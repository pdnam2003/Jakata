package com.example.lanchat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.lanchat.model.ChatMessage;
import com.example.lanchat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final Map<String, String> usernameBySessionId = new ConcurrentHashMap<>();
    private final Map<String, String> sessionIdByUsername = new ConcurrentHashMap<>();
    private final ChatMessageRepository repository;

    @Autowired
    public ChatService(ChatMessageRepository repository) {
        this.repository = repository;
    }

    public void registerSession(String sessionId, String username) {
        usernameBySessionId.put(sessionId, username);
        sessionIdByUsername.put(username, sessionId);
    }

    public String removeSession(String sessionId) {
        String username = usernameBySessionId.remove(sessionId);
        if (username != null) {
            sessionIdByUsername.remove(username);
        }
        return username;
    }

    public List<String> getActiveUsers() {
        List<String> users = new ArrayList<>(sessionIdByUsername.keySet());
        Collections.sort(users, String.CASE_INSENSITIVE_ORDER);
        return users;
    }

    public void saveMessage(ChatMessage message) {
        repository.save(message);
    }

    public List<ChatMessage> loadRecentMessages(String username, int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "timestamp"));
        List<ChatMessage> history = repository.findRecentForUser(username, pageable);
        Collections.reverse(history);
        return history;
    }
}
