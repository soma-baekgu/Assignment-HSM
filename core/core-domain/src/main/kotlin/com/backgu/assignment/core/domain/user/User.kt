package com.backgu.assignment.core.domain.user

import com.backgu.assignment.core.domain.support.PasswordEncryptor

class User private constructor(
    val id: Long,
    val name: String,
    val email: String,
    var password: String,
) {
    fun passwordEncode(passwordEncryptor: PasswordEncryptor) {
        password = passwordEncryptor.encode(password)
    }

    fun passwordMatch(
        passwordEncryptor: PasswordEncryptor,
        password: String,
    ) {
        require(passwordEncryptor.matches(password, this.password)) { "비밀번호가 일치하지 않습니다." }
    }

    companion object {
        fun of(
            id: Long,
            name: String,
            email: String,
            password: String,
        ): User =
            User(
                id,
                name,
                email,
                password,
            )
    }
}
