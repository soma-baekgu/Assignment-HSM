package com.backgu.assignment.core.api.support

import com.backgu.assignment.core.domain.support.SessionManager
import com.backgu.assignment.core.domain.user.User
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Component

@Component
class HttpSessionManager(
    private val session: HttpSession,
) : SessionManager {
    override fun setAttribute(
        key: String,
        value: User,
    ) {
        session.setAttribute(key, value)
    }

    override fun getAttribute(key: String): User? = session.getAttribute(key) as User?

    override fun removeAttribute(key: String) {
        session.removeAttribute(key)
    }
}
