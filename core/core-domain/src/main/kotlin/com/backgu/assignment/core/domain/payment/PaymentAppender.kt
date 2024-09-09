package com.backgu.assignment.core.domain.payment

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PaymentAppender(
    private val paymentRepository: PaymentRepository,
) {
    @Transactional
    fun append(payment: Payment) {
        paymentRepository.save(payment)
    }
}
