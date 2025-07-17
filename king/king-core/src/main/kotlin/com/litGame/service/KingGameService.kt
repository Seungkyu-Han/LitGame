package com.litGame.service

interface KingGameService {

    fun createGame(name: String?, capacity: Int): Int

    fun joinGame(gameRoomId: Int): Boolean
}