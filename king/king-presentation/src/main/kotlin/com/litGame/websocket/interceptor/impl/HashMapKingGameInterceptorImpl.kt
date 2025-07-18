package com.litGame.websocket.interceptor.impl

import com.litGame.service.KingGameService
import com.litGame.websocket.interceptor.KingGameInterceptor
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component

@Component
class HashMapKingGameInterceptorImpl(
    private val kingGameService: KingGameService
): KingGameInterceptor {

    private val sessionIdGameMap = HashMap<String, Int>()

    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {

        val accessor = StompHeaderAccessor.wrap(message)

        if(accessor.command == StompCommand.SUBSCRIBE)
        {
            joinGame(accessor)
        }

        return super.preSend(message, channel)
    }

    override fun postSend(message: Message<*>, channel: MessageChannel, sent: Boolean) {

        val accessor = StompHeaderAccessor.wrap(message)

        if(accessor.command == StompCommand.DISCONNECT)
        {
            exitGame(accessor)
        }

        super.postSend(message, channel, sent)
    }

    fun exitGame(stompHeaderAccessor: StompHeaderAccessor) {

        val sessionId = stompHeaderAccessor.sessionId

        if(sessionId != null && sessionIdGameMap.containsKey(sessionId)){
            kingGameService.exitGame(sessionIdGameMap[sessionId]!!)
        }
    }

    fun joinGame(stompHeaderAccessor: StompHeaderAccessor){

        val gameRoomId = getDestination(stompHeaderAccessor)
        val sessionId = stompHeaderAccessor.sessionId


        if (!kingGameService.joinGame(gameRoomId)){
            throw IllegalStateException()
        }

        if(sessionId != null)
            this.sessionIdGameMap[sessionId] = gameRoomId
    }

    private fun getDestination(stompHeaderAccessor: StompHeaderAccessor): Int {
        val destination = stompHeaderAccessor.destination ?: throw IllegalStateException()

        return destination.removePrefix("/sub/").toIntOrNull() ?: throw IllegalStateException()
    }
}