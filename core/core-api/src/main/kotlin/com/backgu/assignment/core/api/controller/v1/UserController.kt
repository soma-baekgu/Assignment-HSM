package com.backgu.assignment.core.api.controller.v1

import com.backgu.assignment.core.api.controller.v1.request.LoginRequest
import com.backgu.assignment.core.api.controller.v1.request.SignUpRequest
import com.backgu.assignment.core.api.controller.v1.response.SignUpResponse
import com.backgu.assignment.core.api.support.response.ApiResponse
import com.backgu.assignment.core.domain.user.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody request: SignUpRequest,
    ): ApiResponse<SignUpResponse> = ApiResponse.success(SignUpResponse.of(userService.signUp(request.toUser())))

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
        httpServletRequest: HttpServletRequest,
    ): ApiResponse<Unit> {
        val sessionId = userService.login(request.toLoginUser())

        httpServletRequest.session.setAttribute("AUTH", sessionId)

        return ApiResponse.success(Unit)
    }
}
