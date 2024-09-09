package com.backgu.assignment.storage.db.core.payment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PaymentJpaRepository : JpaRepository<PaymentEntity, Long> {
    @Query(
        """
        SELECT p
        FROM PaymentEntity p
        WHERE p.userId = :userId
        AND YEAR(p.createdAt) = :year
        AND MONTH(p.createdAt) = :month
        """,
    )
    fun findByUserIdAndYearAndMonth(
        userId: Long,
        year: Int,
        month: Int,
    ): List<PaymentEntity>
}
