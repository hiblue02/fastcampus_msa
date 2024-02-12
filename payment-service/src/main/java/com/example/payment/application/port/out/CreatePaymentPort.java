package com.example.payment.application.port.out;

import com.example.payment.domain.Payment;

public interface CreatePaymentPort {
    Payment createPayment(
            String requestMembershipId,
            String requestPrice,
            String franchiseId,
            String franchiseFeeRate
    );
}
