package com.backgu.assignment.core.domain.user

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
) {
    fun read(email: String): User =
        userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("유저가 존재하지 않습니다.")
}
