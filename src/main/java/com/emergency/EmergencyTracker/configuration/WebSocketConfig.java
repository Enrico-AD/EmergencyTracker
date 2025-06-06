package com.emergency.EmergencyTracker.configuration;

import com.emergency.EmergencyTracker.configuration.interceptors.WebSocketHandshakeInterceptor;
import com.emergency.EmergencyTracker.configuration.security.SessionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    private static final Map<String, WebSocketSession> activeSessions = new ConcurrentHashMap<>();


    private final SessionManager sessionManager;

    public WebSocketConfig(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setAllowedOriginPatterns("*")
                .addInterceptors(new WebSocketHandshakeInterceptor(sessionManager))
                .withSockJS();

        registry.addEndpoint("/notificacaoChat")
                .setAllowedOriginPatterns("*")
                .withSockJS();

        registry.addEndpoint("/notificacao")
                .setAllowedOriginPatterns("*")
                .withSockJS();

    }


    private String extractUserId(ServerHttpRequest request) {
        // Extraia o ID do usuário, por exemplo, a partir de um cabeçalho ou parâmetro de consulta
        return "uniqueUserId"; // Substitua pela lógica real
    }


//https://stackoverflow.com/questions/41156055/error-during-websocket-handshake-unexpected-response-code-400-when-using-sprin
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue");

    }
}
/*
 * @Configuration
 * 
 * @EnableWebSocket
 * public class WebSocketConfig implements WebSocketConfigurer {
 * private final WebSocketHandler webSocketHandler;
 * 
 * public WebSocketConfig(WebSocketHandler webSocketHandler) {
 * this.webSocketHandler = webSocketHandler;
 * }
 * 
 * @Override
 * public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
 * // Registra o WebSocketHandler para a URL desejada
 * registry.addHandler(webSocketHandler, "/chat").setAllowedOrigins("*");
 * }
 */
/*
 * @Override
 * public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
 * registry.addHandler(new ChatWebSocketHandler(),
 * "/chat").setAllowedOrigins("*");
 * }
 * 
 * 
 * }
 */
/*
    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS()
                .setInterceptors(new HttpSessionHandshakeInterceptor());
        logger.info("WebSocket endpoint '/chat' registered.");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
        logger.info("Message broker configured with application prefix '/app' and topic prefix '/topic'.");
    }
  /*  @Bean
    WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/chat/**")
                       // .allowedMethods("GET", "POST", "PUT", "DELETE")
                       // .allowedHeaders("*")
                        .allowedOrigins("http://localhost:3000");
            }
        };
    }

}
*/