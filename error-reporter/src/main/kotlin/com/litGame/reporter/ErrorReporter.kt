package com.litGame.reporter

import java.time.LocalDateTime

interface ErrorReporter {

    fun reportError(content: String, localDateTime: LocalDateTime)
}