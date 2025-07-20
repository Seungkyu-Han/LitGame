package com.litGame.service.impl

import com.litGame.domain.KingGame
import com.litGame.exception.KingGameException
import com.litGame.exception.KingGameExceptionStatus
import com.litGame.service.KingGameService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class CircularNumberKingGameService: KingGameService {

    private val kingGames = mutableMapOf<Int, KingGame>()

    private var lastGameRoomId = 1000

    override fun createGame(name: String?, capacity: Int): Int {
        synchronized(this) {
            val gameRoomId = searchNextGameRoomId()
            val gameName = name ?: "왕게임$gameRoomId"
            val kingGame = KingGame(
                id = gameRoomId,
                name = gameName,
                capacity = capacity
            )

            kingGames[gameRoomId] = kingGame
            return gameRoomId
        }
    }

    override fun joinGame(gameRoomId: Int): Boolean {
        val kingGame = kingGames[gameRoomId] ?: throw KingGameException(KingGameExceptionStatus.GAME_NOT_EXIST)

        synchronized(this){
            return if(kingGame.canJoin()){
                kingGame.join()
                true
            }
            else throw KingGameException(KingGameExceptionStatus.ROOM_IS_FULL)
        }
    }

    override fun exitGame(gameRoomId: Int) {
        val kingGame = kingGames[gameRoomId] ?: return

        synchronized(this){
            kingGame.exit()

            if(kingGame.isDelete())
                this.kingGames.remove(gameRoomId)
        }
    }

    override fun manageGame(): List<KingGame> {
        return kingGames.values.toList()
    }

    private fun searchNextGameRoomId(): Int {
        var curGameRoomId = lastGameRoomId + 1

        while(kingGames.containsKey(curGameRoomId) && curGameRoomId != lastGameRoomId) {
            curGameRoomId++
            if(curGameRoomId >= 10000)
                curGameRoomId = 1000
        }

        if(curGameRoomId == lastGameRoomId)
            throw KingGameException(KingGameExceptionStatus.NOT_AVAILABLE_ROOM)

        this.lastGameRoomId = curGameRoomId

        return curGameRoomId
    }

    @Scheduled(cron = "0 */1 * * * *")
    fun removeGameRoomScheduler(){
        synchronized(this) {
            val iterator = kingGames.entries.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                if (entry.value.isDelete()) {
                    iterator.remove()
                }
            }
        }
    }
}