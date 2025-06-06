package com.emergency.EmergencyTracker.configuration.security;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class SessionManager {
    private final Map<String, String> sessionUserMap = new ConcurrentHashMap<>();

    // Associa o sessionID ao usuário
    public void associateUser(String sessionId, String userId) {
        sessionUserMap.put(sessionId, userId);
    }

    // Recupera o usuário pelo sessionID
    public String getUser(String sessionId) {
        return sessionUserMap.get(sessionId);
    }

    // Remove associação quando o usuário desconecta
    public void removeUser(String sessionId) {
        sessionUserMap.remove(sessionId);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        this.removeUser(sessionId);
    }
}

