package com.litGame.reporter

import java.time.LocalDateTime

interface ErrorReporter {

    fun reportError(ex: Throwable, localDateTime: LocalDateTime)
}