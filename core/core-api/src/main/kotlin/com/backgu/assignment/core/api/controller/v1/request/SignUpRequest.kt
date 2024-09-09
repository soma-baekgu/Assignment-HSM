package com.backgu.assignment.core.api.controller.v1.request

import com.backgu.assignment.core.domain.user.User

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
) {
    fun toUser(): User =
        User.of(
            id = 0L,
            name = name,
            email = email,
            password = password,
        )
}
