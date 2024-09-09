package com.backgu.assignment.core.api.controller.v1.response

import com.backgu.assignment.core.domain.payment.Payment

class PaymentsResponse(
    val payments: List<PaymentResponse>,
) {
    companion object {
        fun of(payments: List<Payment>): PaymentsResponse =
            PaymentsResponse(
                payments = payments.map { PaymentResponse.of(it) },
            )
    }
}
