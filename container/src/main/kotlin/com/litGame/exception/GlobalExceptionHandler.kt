package com.litGame.exception

import com.litGame.reporter.ErrorReporter
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler(
    private val errorReporter: ErrorReporter
) {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {

        errorReporter.reportError(e, LocalDateTime.now())

        return ResponseEntity.internalServerError().build()
    }
}