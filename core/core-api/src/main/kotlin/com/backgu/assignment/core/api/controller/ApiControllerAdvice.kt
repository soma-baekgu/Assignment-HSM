package com.backgu.assignment.core.api.controller

import com.backgu.assignment.core.api.support.error.CoreApiException
import com.backgu.assignment.core.api.support.error.ErrorType
import com.backgu.assignment.core.api.support.response.ApiResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.logging.LogLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val log = KotlinLogging.logger {}

@RestControllerAdvice
class ApiControllerAdvice {
    @ExceptionHandler(CoreApiException::class)
    fun handleCoreApiException(e: CoreApiException): ResponseEntity<ApiResponse<Any>> {
        when (e.errorType.logLevel) {
            LogLevel.ERROR -> log.error(e) { "CoreApiException : ${e.message}" }
            LogLevel.WARN -> log.warn(e) { "CoreApiException : ${e.message}" }
            else -> log.info(e) { "CoreApiException : ${e.message}" }
        }

        return ResponseEntity(ApiResponse.error(e.errorType, e.data), e.errorType.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Any>> {
        log.error(e) { "Exception : ${e.message}" }
        return ResponseEntity(ApiResponse.error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.status)
    }
}
