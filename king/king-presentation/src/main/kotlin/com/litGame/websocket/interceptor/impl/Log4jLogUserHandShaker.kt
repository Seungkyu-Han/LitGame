package com.litGame.websocket.interceptor.impl

import com.litGame.websocket.interceptor.LogUserHandshakeInterceptor
import org.slf4j.LoggerFactory
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketHandler
import java.lang.Exception
import java.net.InetSocketAddress

@Component
class Log4jLogUserHandShaker: LogUserHandshakeInterceptor{

    private val logger = LoggerFactory.getLogger(Log4jLogUserHandShaker::class.java)

    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {

        val remoteAddress: InetSocketAddress = request.remoteAddress

        logger.info("{} is connected", remoteAddress.hostName)

        return true
    }

    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        exception: Exception?
    ) = Unit

}