package com.litGame.exception

import com.litGame.reporter.ErrorReporter
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.resource.NoResourceFoundException
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler(
    private val errorReporter: ErrorReporter
) {
    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(e: NoResourceFoundException): ResponseEntity<Void> {
        return ResponseEntity.notFound().build()
    }


    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {

        errorReporter.reportError(e, LocalDateTime.now())

        return ResponseEntity.internalServerError().build()
    }
}