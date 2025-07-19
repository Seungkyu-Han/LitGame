package com.litGame.chat

import com.litGame.dto.res.MessageRes
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class ChatHandler(
    private val simpMessagingTemplate: SimpMessagingTemplate
) {

    fun sendMessage(content: String, sender: String, gameRoomId: Int) {
        simpMessagingTemplate.convertAndSend("/sub/$gameRoomId", MessageRes(sender = sender, content = content))
    }
}