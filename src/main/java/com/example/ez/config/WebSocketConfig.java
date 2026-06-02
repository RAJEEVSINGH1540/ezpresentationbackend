// com.example.ez.config.WebSocketConfig.java
package com.example.ez.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(
                "/topic",   // Chat: /topic/chat/{sessionId}
                // CaseStudy: /topic/notifications
                "/queue"    // Private messages
        );
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // ✅ Native WebSocket
        registry
                .addEndpoint("/ws")
                .setAllowedOriginPatterns("*");

        // ✅ SockJS fallback (your chat uses this)
        registry
                .addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}