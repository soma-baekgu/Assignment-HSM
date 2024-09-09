package com.backgu.assignment.core.domain.payment

import com.backgu.assignment.core.domain.user.User
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentAppender: PaymentAppender,
    private val paymentReader: PaymentReader,
) {
    fun makePayment(payment: Payment) {
        // TODO: orderFinder.find(user, payment.orderId)

        // TODO:  외부 api 결제 로직 호출

        paymentAppender.append(payment)
    }

    fun findPayment(
        user: User,
        year: Int,
        month: Int,
    ): List<Payment> = paymentReader.read(user, year, month)
}
