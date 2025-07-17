package com.litGame.websocket.interceptor.impl

import com.litGame.websocket.interceptor.AssignUserIdInterceptor
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component

@Component
class RandomUserIdInterceptor: AssignUserIdInterceptor{

    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {

        val accessor = StompHeaderAccessor.wrap(message)

        if(accessor.command == StompCommand.CONNECT)
        {
            val userId = "사용자${(1000..9999).random()}"

            accessor.sessionAttributes?.put("userId", userId)
        }

        return super.preSend(message, channel)
    }
}