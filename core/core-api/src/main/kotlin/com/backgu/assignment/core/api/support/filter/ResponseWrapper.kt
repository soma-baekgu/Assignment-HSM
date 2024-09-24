package com.backgu.assignment.core.api.support.filter

import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.util.ContentCachingResponseWrapper

class ResponseWrapper(
    response: HttpServletResponse,
) : ContentCachingResponseWrapper(response)
