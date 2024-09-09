package com.backgu.assignment.core.api.controller.v1.request

import com.backgu.assignment.core.domain.user.LoginUser

data class LoginRequest(
    val email: String,
    val password: String,
) {
    fun toLoginUser(): LoginUser = LoginUser.of(email, password)
}
