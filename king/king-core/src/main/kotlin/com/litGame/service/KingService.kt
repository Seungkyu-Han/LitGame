package com.litGame.service

interface KingService {

    fun sendMessage(message: String, roomId: String): String

    fun createGameRoom(): Int
}