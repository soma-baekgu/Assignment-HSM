package com.backgu.assignment.core.domain.support

interface PasswordEncryptor {
    fun encode(password: String): String

    fun matches(
        password: String,
        encodedPassword: String,
    ): Boolean
}
