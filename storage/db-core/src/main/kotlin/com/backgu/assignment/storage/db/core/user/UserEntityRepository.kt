package com.backgu.assignment.storage.db.core.user

import com.backgu.assignment.core.domain.user.User
import com.backgu.assignment.core.domain.user.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserEntityRepository(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {
    override fun save(user: User): User = userJpaRepository.save(UserEntity.of(user = user)).toDomain()

    override fun findByEmail(email: String): User? = userJpaRepository.findByEmail(email)?.toDomain()
}
