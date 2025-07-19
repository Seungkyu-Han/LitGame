package com.litGame.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.litGame.dto.res.GameErrorRes
import com.litGame.exception.KingGameException
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler

@Configuration
class StompErrorHandlerConfig(
    private val objectMapper: ObjectMapper
): StompSubProtocolErrorHandler() {

    override fun handleClientMessageProcessingError(
        clientMessage: Message<ByteArray>?,
        ex: Throwable
    ): Message<ByteArray>? {
        val accessor = StompHeaderAccessor.create(StompCommand.ERROR)
        accessor.setLeaveMutable(true)

        val payload: ByteArray = when(val exception = ex.cause)
        {
            is KingGameException -> {
                val errorDto = GameErrorRes(status = exception.kingGameExceptionStatus)
                objectMapper.writeValueAsBytes(errorDto)
            }
            else -> {
                throw ex
            }
        }

        return MessageBuilder.createMessage(payload, accessor.messageHeaders)
    }
}