package com.litGame.service

interface ChatService {

    fun sendMessage(message: String, gameRoomId: Int)
}