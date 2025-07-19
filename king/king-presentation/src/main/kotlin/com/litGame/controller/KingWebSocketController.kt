package com.litGame.controller

import com.litGame.chat.ChatHandler
import com.litGame.dto.req.PlayGameDto
import com.litGame.enums.TypeEnum
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.RestController

@RestController
class KingWebSocketController(
    private val chatHandler: ChatHandler
) {

    @MessageMapping("/{roomId}")
    fun publishMessage(
        playGameDto: PlayGameDto,
        @DestinationVariable("roomId") gameRoomId: Int,
        simpleMessageHeaderAccessor: SimpMessageHeaderAccessor
    ){
        val userId = (simpleMessageHeaderAccessor.sessionAttributes?.get("userId") as? String) ?: "익명의 사용자"

        when(playGameDto.type)
        {
            TypeEnum.MESSAGE -> chatHandler.sendMessage(playGameDto.content, userId, gameRoomId)
            else -> println("${playGameDto.content} - $userId")
        }

    }
}