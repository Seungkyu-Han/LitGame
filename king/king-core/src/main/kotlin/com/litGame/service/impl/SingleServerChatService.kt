package com.litGame.service.impl

import com.litGame.service.ChatService
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class SingleServerChatService(
    private val messagingTemplate: SimpMessagingTemplate
): ChatService {

    override fun sendMessage(message: String, gameRoomId: Int) {
        messagingTemplate.convertAndSend("/sub/$gameRoomId", message)
    }
}