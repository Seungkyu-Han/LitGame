package com.litGame.websocket

import com.litGame.websocket.interceptor.AssignUserIdInterceptor
import com.litGame.websocket.interceptor.LogUserInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class KingWebSocketConfig(
    private val logUserInterceptor: LogUserInterceptor,
    private val assignUserIdInterceptor: AssignUserIdInterceptor
): WebSocketMessageBrokerConfigurer {

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/websocket/v1/king")
            .setAllowedOrigins("*")
            .withSockJS()

        registry.addEndpoint("/websocket/v1/king")
            .setAllowedOrigins("*")
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/sub")
        registry.setApplicationDestinationPrefixes("/pub")
    }

    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        registration.interceptors(logUserInterceptor)
        registration.interceptors(assignUserIdInterceptor)
        super.configureClientInboundChannel(registration)
    }
}