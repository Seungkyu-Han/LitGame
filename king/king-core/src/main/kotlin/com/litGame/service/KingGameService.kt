package com.litGame.service

import com.litGame.domain.KingGame

interface KingGameService {

    fun createGame(name: String?, capacity: Int): Int

    fun joinGame(gameRoomId: Int): Boolean

    fun exitGame(gameRoomId: Int)

    fun manageGame(): List<KingGame>
}