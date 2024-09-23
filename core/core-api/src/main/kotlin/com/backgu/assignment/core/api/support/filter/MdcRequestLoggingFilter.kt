package com.backgu.assignment.core.api.support.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.MDC
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class MdcRequestLoggingFilter : Filter {
    override fun doFilter(
        servletRequest: ServletRequest,
        servletResponse: ServletResponse,
        filterChain: FilterChain,
    ) {
        val uuid = UUID.randomUUID()
        MDC.put("requestId", uuid.toString())
        filterChain.doFilter(servletRequest, servletResponse)
        MDC.clear()
    }
}
