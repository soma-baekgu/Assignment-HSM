package com.backgu.assignment.core.api.support.filter

import com.backgu.assignment.core.api.support.filter.RequestLoggingFilter.Companion.VISIBLE_TYPES
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import my.study.springlogging.global.filters.RequestWrapper
import org.apache.naming.SelectorContext.prefix
import org.slf4j.MDC
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.StreamUtils
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingResponseWrapper
import java.io.InputStream
import java.util.UUID

private val log = KotlinLogging.logger {}

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class RequestLoggingFilter : OncePerRequestFilter() {
    companion object {
        val VISIBLE_TYPES =
            listOf(
                MediaType.valueOf("text/*"),
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_XML,
                MediaType.valueOf("application/*+json"),
                MediaType.valueOf("application/*+xml"),
                MediaType.MULTIPART_FORM_DATA,
            )
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        MDC.put("traceId", UUID.randomUUID().toString())
        try {
            if (isAsyncDispatch(request)) {
                filterChain.doFilter(request, response)
            } else {
                doFilterWrapped(RequestWrapper(request), ContentCachingResponseWrapper(response), filterChain)
            }
        } finally {
            MDC.clear()
        }
    }

    protected fun doFilterWrapped(
        request: RequestWrapper,
        response: ContentCachingResponseWrapper,
        filterChain: FilterChain,
    ) {
        try {
            logRequest(request)
            filterChain.doFilter(request, response)
        } finally {
            logResponse(response)
            response.copyBodyToResponse()
        }
    }

    private fun logRequest(request: RequestWrapper) {
        val queryString = request.queryString
        log.info {
            "Request : ${request.method} uri=[${if (queryString == null) request.requestURI else request.requestURI + queryString}] content-type=[${request.contentType}]"
        }

        logPayload("Request", request.contentType, request.inputStream)
    }

    private fun logResponse(response: ContentCachingResponseWrapper) {
        logPayload("Response", response.contentType, response.contentInputStream)
    }

    private fun logPayload(
        prefix: String,
        contentType: String?,
        inputStream: InputStream,
    ) {
        val visible = isVisible(MediaType.valueOf(contentType ?: "application/json"))
        if (visible) {
            val content = StreamUtils.copyToByteArray(inputStream)
            if (content.isNotEmpty()) {
                val contentString = String(content)
                log.info { "$prefix Payload: $contentString" }
            }
        } else {
            log.info { "$prefix Payload: Binary Content" }
        }
    }

    private fun isVisible(mediaType: MediaType): Boolean = VISIBLE_TYPES.any { visibleType -> visibleType.includes(mediaType) }
}
