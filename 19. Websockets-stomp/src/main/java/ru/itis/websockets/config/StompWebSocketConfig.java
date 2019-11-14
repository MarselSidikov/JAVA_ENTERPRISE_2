package ru.itis.websockets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import ru.itis.websockets.interceptors.CustomInterceptor;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Autowired
    private CustomInterceptor interceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // включил in memory MessageBroker
        config.enableSimpleBroker("/topic");
        // указал, куда могут быть направлены сообщения
        config.setApplicationDestinationPrefixes("/app");
    }

    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        // обозначили точку подключения
        stompEndpointRegistry.addEndpoint("/messages").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(interceptor);
    }
}
