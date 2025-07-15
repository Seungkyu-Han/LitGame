package com.litGame.websocket

import com.litGame.dto.req.PlayGameDto
import com.litGame.enums.TypeEnum
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
        playGameDto: PlayGameDto,
        @DestinationVariable("roomId") roomId: String,
        simpleMessageHeaderAccessor: SimpMessageHeaderAccessor
    ){
        val sessionId = simpleMessageHeaderAccessor.sessionId

        when(playGameDto.type)
        {
            TypeEnum.MESSAGE -> kingService.sendMessage("${playGameDto.content} - $sessionId", roomId)
            else -> println("${playGameDto.content} - $sessionId")
        }

    }
}