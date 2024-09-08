package com.backgu.assignment.core.api.support

import com.backgu.assignment.core.domain.support.PasswordEncryptor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class BCryptPasswordEncoder(
    private val encoder: PasswordEncoder,
) : PasswordEncryptor {
    override fun encode(password: String): String = encoder.encode(password)

    override fun matches(
        password: String,
        encodedPassword: String,
    ): Boolean = encoder.matches(password, encodedPassword)
}
