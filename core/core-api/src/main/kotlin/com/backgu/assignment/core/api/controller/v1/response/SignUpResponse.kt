package com.backgu.assignment.core.api.controller.v1.response

class SignUpResponse(
    val userId: Long,
) {
    companion object {
        fun of(userId: Long): SignUpResponse = SignUpResponse(userId)
    }
}
