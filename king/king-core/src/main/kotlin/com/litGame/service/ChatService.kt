package com.litGame.service

interface ChatService {

    fun sendMessage(content: String, sender: String, gameRoomId: Int)
}