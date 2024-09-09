package com.backgu.assignment.core.api.controller.v1.request

import com.backgu.assignment.core.domain.payment.Payment
import java.math.BigDecimal

class PaymentRequest(
    private val orderId: Long,
    private val amount: BigDecimal,
) {
    fun toPayment(userId: Long): Payment =
        Payment.of(
            userId = userId,
            orderId = orderId,
            amount = amount,
        )
}
