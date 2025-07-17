package com.litGame.websocket

import com.litGame.websocket.interceptor.AssignUserIdInterceptor
import com.litGame.websocket.interceptor.LogUserHandshakeInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class KingWebSocketConfig(
    private val assignUserIdInterceptor: AssignUserIdInterceptor,
    private val logUserHandshakeInterceptor: LogUserHandshakeInterceptor
): WebSocketMessageBrokerConfigurer {

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/websocket/v1/king")
            .setAllowedOrigins("*")
            .addInterceptors(logUserHandshakeInterceptor)
            .withSockJS()

        registry.addEndpoint("/websocket/v1/king")
            .addInterceptors(logUserHandshakeInterceptor)
            .setAllowedOrigins("*")
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/sub")
        registry.setApplicationDestinationPrefixes("/pub")
    }

    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        registration.interceptors(assignUserIdInterceptor)
        super.configureClientInboundChannel(registration)
    }
}