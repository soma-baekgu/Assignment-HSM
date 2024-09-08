package com.backgu.assignment.core.domain.user

import org.springframework.stereotype.Repository

@Repository
interface UserRepository {
    fun save(user: User): User

    fun findByEmail(email: String): User?
}
