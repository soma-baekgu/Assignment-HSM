package com.backgu.assignment.core.api.controller.v1

import com.backgu.assignment.core.api.controller.v1.request.LoginRequest
import com.backgu.assignment.core.api.controller.v1.request.SignUpRequest
import com.backgu.assignment.core.api.controller.v1.response.SignUpResponse
import com.backgu.assignment.core.api.support.CookieManger
import com.backgu.assignment.core.api.support.response.ApiResponse
import com.backgu.assignment.core.domain.user.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserController(
    private val userService: UserService,
    private val cookieManger: CookieManger,
) {
    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody request: SignUpRequest,
    ): ApiResponse<SignUpResponse> = ApiResponse.success(SignUpResponse.of(userService.signUp(request.toUser())))

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
        response: HttpServletResponse,
    ): ApiResponse<Unit> {
        val sessionId = userService.login(request.toLoginUser())

        cookieManger.createSessionCookie(sessionId).let {
            response.addCookie(it)
        }

        return ApiResponse.success(Unit)
    }
}
