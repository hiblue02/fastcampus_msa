package com.example.payment.application.service;

import com.example.common.UseCase;
import com.example.payment.application.port.in.RequestPaymentCommand;
import com.example.payment.application.port.in.RequestPaymentUseCase;
import com.example.payment.application.port.out.CreatePaymentPort;
import com.example.payment.application.port.out.GetMembershipPort;
import com.example.payment.application.port.out.GetRegisteredBankAccountPort;
import com.example.payment.domain.Payment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class PaymentService implements RequestPaymentUseCase {
    private final CreatePaymentPort createPaymentPort;
    private final GetMembershipPort getMembershipPort;
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;


    @Override
    public Payment requestPayment(RequestPaymentCommand command) {
        // 1. 맴버십 유효성 확인
        getMembershipPort.getMembership(command.getRequestMembershipId());
        // 2. 계좌 유효성 확인
        getRegisteredBankAccountPort.getRegisteredBankAccount(command.getRequestMembershipId());

        return createPaymentPort.createPayment(
                command.getRequestMembershipId(),
                command.getRequestPrice(),
                command.getFranchiseId(),
                command.getFranchiseFeeRate()
        );
    }
}
