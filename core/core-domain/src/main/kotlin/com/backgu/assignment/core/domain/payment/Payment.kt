package com.backgu.assignment.core.domain.payment

import java.math.BigDecimal

class Payment private constructor(
    val id: Long,
    val userId: Long,
    val orderId: Long,
    val amount: BigDecimal,
) {
    companion object {
        fun of(
            id: Long = 0L,
            userId: Long,
            orderId: Long,
            amount: BigDecimal,
        ): Payment =
            Payment(
                id = id,
                userId = userId,
                orderId = orderId,
                amount = amount,
            )
    }
}
