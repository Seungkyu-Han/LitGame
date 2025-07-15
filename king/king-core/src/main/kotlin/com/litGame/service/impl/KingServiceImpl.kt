package com.litGame.service.impl

import com.litGame.service.KingService
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

@Service
class KingServiceImpl(
    private val simpMessageSendingOperations: SimpMessageSendingOperations
): KingService {

    override fun sendMessage(message: String, roomId: String): String {
        simpMessageSendingOperations.convertAndSend("/sub/$roomId", message)
        return "SUCCESS"
    }

    override fun createGameRoom(): Int {
        return (1000..9999).random()
    }
}