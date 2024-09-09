package com.backgu.assignment.core.api.controller.v1

import com.backgu.assignment.core.api.controller.v1.request.PaymentRequest
import com.backgu.assignment.core.api.controller.v1.response.PaymentsResponse
import com.backgu.assignment.core.api.support.annotation.Auth
import com.backgu.assignment.core.api.support.response.ApiResponse
import com.backgu.assignment.core.domain.payment.PaymentService
import com.backgu.assignment.core.domain.user.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class PaymentController(
    private val paymentService: PaymentService,
) {
    @PostMapping("/payment")
    fun makePayment(
        @Auth user: User,
        @RequestBody request: PaymentRequest,
    ): ApiResponse<Unit> {
        paymentService.makePayment(request.toPayment(user.id))

        return ApiResponse.success(Unit)
    }

    @GetMapping("/payment")
    fun findPayment(
        @Auth user: User,
        @RequestParam("year") year: Int,
        @RequestParam("month") month: Int,
    ): ApiResponse<PaymentsResponse> = ApiResponse.success(PaymentsResponse.of(paymentService.findPayment(user, year, month)))
}
