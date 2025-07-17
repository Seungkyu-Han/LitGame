package com.litGame.domain

import com.litGame.enums.GameStatus
import java.time.LocalDateTime

class KingGame(
    private val number: Int,
    private var name: String,
    private var capacity: Int,
    private var currentCount: Int = 0,
    private var gameStatus: GameStatus = GameStatus.PENDING,
    private val createdAt: LocalDateTime = LocalDateTime.now(),
) {

    fun isPending() = this.gameStatus == GameStatus.PENDING

    fun isEmpty() = this.currentCount == 0

    fun canJoin() = (gameStatus == GameStatus.READY) && (this.capacity > this.currentCount)

    fun join() {
        this.currentCount ++
    }

    fun isDelete() = (this.gameStatus == GameStatus.PENDING)
            && (this.currentCount == 0)
            && (LocalDateTime.now().isAfter(createdAt.plusMinutes(5)))


}