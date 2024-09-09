package com.backgu.assignment.core.api.controller.v1.response

import com.backgu.assignment.core.domain.payment.Payment
import java.math.BigDecimal

data class PaymentResponse(
    val id: Long,
    val userId: Long,
    val orderId: Long,
    val amount: BigDecimal,
) {
    companion object {
        fun of(payment: Payment) =
            PaymentResponse(
                id = payment.id,
                userId = payment.userId,
                orderId = payment.orderId,
                amount = payment.amount,
            )
    }
}
