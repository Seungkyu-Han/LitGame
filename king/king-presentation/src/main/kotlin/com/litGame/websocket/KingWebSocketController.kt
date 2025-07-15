package com.litGame.websocket

import com.litGame.dto.TestDto
import com.litGame.service.KingService
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.RestController

@RestController
class KingWebSocketController(
    private val kingService: KingService
) {

    @MessageMapping("/{roomId}")
    fun publishMessage(
        message: TestDto,
        @DestinationVariable("roomId") roomId: String,
        simpleMessageHeaderAccessor: SimpMessageHeaderAccessor
    ){
        val sessionId = simpleMessageHeaderAccessor.sessionId


        kingService.sendMessage("$message - $sessionId", roomId)
    }
}