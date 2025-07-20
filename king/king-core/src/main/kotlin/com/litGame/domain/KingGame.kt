package com.litGame.domain

import com.litGame.enums.GameStatus
import java.time.LocalDateTime

class KingGame(
    private val id: Int,
    private var name: String,
    private var capacity: Int,
    private var currentCount: Int = 0,
    private var gameStatus: GameStatus = GameStatus.PENDING,
    private val createdAt: LocalDateTime = LocalDateTime.now(),
) {

    fun isPending() = this.gameStatus == GameStatus.PENDING

    fun isEmpty() = this.currentCount == 0

    fun canJoin() = (gameStatus == GameStatus.READY || gameStatus == GameStatus.PENDING) && (this.capacity > this.currentCount)

    fun join() {
        if(gameStatus == GameStatus.PENDING) {
            this.gameStatus = GameStatus.READY
        }
        this.currentCount ++
    }

    fun exit(){
        this.currentCount --
        if(this.currentCount <= 0){
            this.destroy()
        }
    }

    private fun destroy() {
        this.gameStatus = GameStatus.DESTROYED
    }

    fun isDelete() = (this.gameStatus == GameStatus.DESTROYED) || ((this.gameStatus == GameStatus.PENDING)
            && (this.currentCount == 0)
            && (LocalDateTime.now().isAfter(createdAt.plusMinutes(5))))

}