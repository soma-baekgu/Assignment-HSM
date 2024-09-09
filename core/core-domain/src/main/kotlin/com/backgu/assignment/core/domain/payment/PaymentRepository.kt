package com.backgu.assignment.core.domain.payment

import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository {
    fun save(payment: Payment): Payment

    fun findPayment(
        userId: Long,
        year: Int,
        month: Int,
    ): List<Payment>
}
