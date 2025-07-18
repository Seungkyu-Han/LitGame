package com.litGame.service.impl

import com.litGame.domain.KingGame
import com.litGame.service.KingGameService
import org.springframework.stereotype.Service

@Service
class RandomKingGameService: KingGameService {

    private val kingGames = mutableMapOf<Int, KingGame>()

    override fun createGame(name: String?, capacity: Int): Int {
        val gameRoomId = (1000..9999).random()
        val gameName = name ?: "왕게임$gameRoomId"
        val kingGame = KingGame(
            id = gameRoomId,
            name = gameName,
            capacity = capacity
        )

        kingGames[gameRoomId] = kingGame
        return gameRoomId
    }

    override fun joinGame(gameRoomId: Int): Boolean {
        val kingGame = kingGames[gameRoomId] ?: return false

        return if(kingGame.canJoin()){
            kingGame.join()
             true
        }
        else false
    }

    override fun exitGame(gameRoomId: Int) {

        val kingGame = kingGames[gameRoomId] ?: return

        kingGame.exit()
    }
}