package com.backgu.assignment.storage.db.core.payment

import com.backgu.assignment.core.domain.payment.Payment
import com.backgu.assignment.storage.db.core.common.BaseEntity
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
class PaymentEntity(
    val userId: Long,
    val orderId: Long,
    val amount: BigDecimal,
) : BaseEntity() {
    fun toDomain(): Payment =
        Payment.of(
            id = id,
            userId = userId,
            orderId = orderId,
            amount = amount,
        )

    companion object {
        fun of(payment: Payment): PaymentEntity =
            PaymentEntity(
                userId = payment.userId,
                orderId = payment.orderId,
                amount = payment.amount,
            )
    }
}
