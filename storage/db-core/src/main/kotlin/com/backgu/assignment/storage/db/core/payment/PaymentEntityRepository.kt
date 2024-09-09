package com.backgu.assignment.storage.db.core.payment

import com.backgu.assignment.core.domain.payment.Payment
import com.backgu.assignment.core.domain.payment.PaymentRepository
import org.springframework.stereotype.Repository

@Repository
class PaymentEntityRepository(
    private val paymentJpaRepository: PaymentJpaRepository,
) : PaymentRepository {
    override fun save(payment: Payment): Payment = paymentJpaRepository.save(PaymentEntity.of(payment = payment)).toDomain()

    override fun findPayment(
        userId: Long,
        year: Int,
        month: Int,
    ): List<Payment> = paymentJpaRepository.findByUserIdAndYearAndMonth(userId, year, month).map { it.toDomain() }
}
