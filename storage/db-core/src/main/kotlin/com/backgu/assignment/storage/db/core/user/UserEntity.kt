package com.backgu.assignment.storage.db.core.user

import com.backgu.assignment.core.domain.user.User
import com.backgu.assignment.storage.db.core.common.BaseEntity
import jakarta.persistence.Entity

@Entity
class UserEntity(
    val name: String,
    val email: String,
    val password: String,
) : BaseEntity() {
    fun toDomain(): User =
        User.of(
            id = id,
            name = name,
            email = email,
            password = password,
        )

    companion object {
        fun of(user: User): UserEntity =
            UserEntity(
                name = user.name,
                email = user.email,
                password = user.password,
            )
    }
}
