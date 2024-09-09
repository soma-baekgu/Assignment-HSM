package com.backgu.assignment.core.api.support.resolver

import com.backgu.assignment.core.api.support.annotation.Auth
import com.backgu.assignment.core.domain.support.SessionManager
import com.backgu.assignment.core.domain.user.User
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthArgumentResolver(
    private val sessionManager: SessionManager,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.parameterType == User::class.java && parameter.hasParameterAnnotation(Auth::class.java)

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any? {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java)
        return sessionManager.getAttribute(
            request?.session?.getAttribute("AUTH") as? String
                ?: throw IllegalArgumentException("Session not found"),
        ) ?: throw IllegalArgumentException("Session not found")
    }
}
