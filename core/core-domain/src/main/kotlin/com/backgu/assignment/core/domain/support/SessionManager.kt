package com.backgu.assignment.core.domain.support

import com.backgu.assignment.core.domain.user.User

interface SessionManager {
    fun setAttribute(
        key: String,
        value: User,
    )

    fun getAttribute(key: String): User?

    fun removeAttribute(key: String)
}
