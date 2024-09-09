package com.backgu.assignment.core.domain.user

data class LoginUser(
    val email: String,
    val password: String,
) {
    companion object {
        fun of(
            email: String,
            password: String,
        ): LoginUser =
            LoginUser(
                email,
                password,
            )
    }
}
