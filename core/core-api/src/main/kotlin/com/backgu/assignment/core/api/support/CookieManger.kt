package com.backgu.assignment.core.api.support

import jakarta.servlet.http.Cookie
import org.springframework.stereotype.Component

@Component
class CookieManger {
    fun createSessionCookie(
        sessionId: String,
        maxAge: Int = 60 * 60 * 24,
    ): Cookie {
        val cookie = Cookie("sessionId", sessionId)
        cookie.isHttpOnly = true
        cookie.maxAge = maxAge
        cookie.path = "/"
        return cookie
    }
}
