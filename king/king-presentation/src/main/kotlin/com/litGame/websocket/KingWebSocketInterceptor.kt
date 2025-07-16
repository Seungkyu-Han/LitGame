package com.litGame.websocket

import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.stereotype.Component


@Component
class KingWebSocketInterceptor: ChannelInterceptor {

    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor = StompHeaderAccessor.wrap(message)

        if(accessor.command == StompCommand.SUBSCRIBE){

            val userId = "사용자${(1000..9999).random()}"

            accessor.sessionAttributes?.put("userId", userId)
        }

        if(accessor.command == StompCommand.DISCONNECT){
            println("I am disconnect")
        }

        return super.preSend(message, channel)
    }
}