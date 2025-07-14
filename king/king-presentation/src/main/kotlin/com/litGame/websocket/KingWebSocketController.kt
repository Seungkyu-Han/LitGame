package com.litGame.websocket

import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class KingWebSocketController {

    @MessageMapping("/{roomId}")
    suspend fun publishMessage(
        simpleMessageHeaderAccessor: SimpMessageHeaderAccessor,
        @DestinationVariable("roomId") roomId: String,
        @RequestBody message: String
    ){
        val sessionId = simpleMessageHeaderAccessor.sessionId

        println(sessionId)


    }
}