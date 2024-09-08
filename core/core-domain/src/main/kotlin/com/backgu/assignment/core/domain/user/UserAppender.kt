package com.backgu.assignment.core.domain.user

import com.backgu.assignment.core.domain.support.PasswordEncryptor
import org.springframework.stereotype.Component

@Component
class UserAppender(
    private val userRepository: UserRepository,
    private val passwordEncryptor: PasswordEncryptor,
) {
    fun signUp(user: User): Long {
        userRepository.findByEmail(user.email)?.let {
            throw IllegalArgumentException("유저가 이미 존재합니다.")
        }

        user.passwordEncode(passwordEncryptor)
        println(user.password)

        return userRepository.save(user).id
    }
}
