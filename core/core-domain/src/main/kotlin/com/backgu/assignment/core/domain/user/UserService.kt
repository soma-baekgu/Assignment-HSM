package com.backgu.assignment.core.domain.user

import com.backgu.assignment.core.domain.support.SessionManager
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userAppender: UserAppender,
    private val userReader: UserReader,
    private val userLoginValidator: UserLoginValidator,
    private val sessionManager: SessionManager,
) {
    fun signUp(user: User): Long = userAppender.signUp(user)

    fun login(loginUser: LoginUser): String {
        val user = userReader.read(loginUser.email)
        userLoginValidator.login(loginUser.password, user)
        // TODO: 생성기를 따로 빼야 할까?
        val sessionId = UUID.randomUUID().toString()
        sessionManager.setAttribute(sessionId, user)
        return sessionId
    }
}
