package com.backgu.assignment.core.domain.user

import com.backgu.assignment.core.domain.support.PasswordEncryptor
import org.springframework.stereotype.Component

@Component
class UserLoginValidator(
    private val passwordEncryptor: PasswordEncryptor,
) {
    fun login(
        password: String,
        user: User,
    ) {
        user.passwordMatch(passwordEncryptor, password)
    }
}
