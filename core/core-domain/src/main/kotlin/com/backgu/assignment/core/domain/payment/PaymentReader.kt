package com.backgu.assignment.core.domain.payment

import com.backgu.assignment.core.domain.user.User
import org.springframework.stereotype.Component

@Component
class PaymentReader(
    private val paymentRepository: PaymentRepository,
) {
    fun read(
        user: User,
        year: Int,
        month: Int,
    ): List<Payment> = paymentRepository.findPayment(user.id, year, month)
}
