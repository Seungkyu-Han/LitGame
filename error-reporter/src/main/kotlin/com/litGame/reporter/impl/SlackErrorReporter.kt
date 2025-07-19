package com.litGame.reporter.impl

import com.litGame.reporter.ErrorReporter
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.io.PrintWriter
import java.io.StringWriter
import java.time.LocalDateTime

@Service
class SlackErrorReporter(
    @Value("\${slack.webhook_url.error}")
    private val slackWebHookUrl: String
): ErrorReporter {

    override fun reportError(ex: Throwable, localDateTime: LocalDateTime) {

        val sw = StringWriter()
        ex.printStackTrace(PrintWriter(sw))

        val payload = mapOf("text" to "${localDateTime}에 $sw")

        WebClient.create()
            .post().uri(slackWebHookUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(payload)
            .retrieve()
            .bodyToMono(Void::class.java)
            .block()

    }
}