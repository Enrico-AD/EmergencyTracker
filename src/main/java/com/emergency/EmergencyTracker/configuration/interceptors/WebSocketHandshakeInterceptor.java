package com.emergency.EmergencyTracker.configuration.interceptors;

import com.emergency.EmergencyTracker.configuration.security.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Configuration
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    @Autowired
    private SessionManager sessionManager;

    public WebSocketHandshakeInterceptor(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            String sessionId = servletRequest.getSession().getId();

            // Obtenha o usuário associado ao sessionID
            String userId = sessionManager.getUser(sessionId);
            if (userId == null) {
                return false; // Não permitir conexão se o usuário não estiver associado
            }

            attributes.put("userId", userId); // Passe o userId para uso posterior
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
