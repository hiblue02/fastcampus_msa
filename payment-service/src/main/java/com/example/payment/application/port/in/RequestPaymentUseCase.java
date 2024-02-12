package com.example.payment.application.port.in;

import com.example.payment.domain.Payment;

public interface RequestPaymentUseCase {
    Payment requestPayment(RequestPaymentCommand command);
}
