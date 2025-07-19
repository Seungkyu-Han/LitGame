package com.litGame.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.litGame.dto.res.GameErrorRes
import com.litGame.exception.KingGameException
import com.litGame.reporter.ErrorReporter
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler
import java.io.PrintWriter
import java.io.StringWriter
import java.time.LocalDateTime

@Configuration
class StompErrorHandlerConfig(
    private val objectMapper: ObjectMapper,
    private val errorReporter: ErrorReporter
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
                val sw = StringWriter()
                exception?.printStackTrace(PrintWriter(sw))
                errorReporter.reportError(sw.toString(), LocalDateTime.now())
                throw ex
            }
        }

        return MessageBuilder.createMessage(payload, accessor.messageHeaders)
    }
}