package com.litGame.websocket.interceptor.impl

import com.litGame.service.KingGameService
import com.litGame.websocket.interceptor.KingGameInterceptor
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component

@Component
class KingGameInterceptorImpl(
    private val kingGameService: KingGameService
): KingGameInterceptor {

    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {

        val accessor = StompHeaderAccessor.wrap(message)

        if(accessor.command == StompCommand.SUBSCRIBE)
        {
            joinGame(accessor)
        }

        return super.preSend(message, channel)
    }

    fun joinGame(stompHeaderAccessor: StompHeaderAccessor){
        val destination = stompHeaderAccessor.destination ?: throw IllegalStateException()

        val gameRoomId = destination.removePrefix("/sub/").toIntOrNull() ?: throw IllegalStateException()

        if (!kingGameService.joinGame(gameRoomId)){
            throw IllegalStateException()
        }
    }
}