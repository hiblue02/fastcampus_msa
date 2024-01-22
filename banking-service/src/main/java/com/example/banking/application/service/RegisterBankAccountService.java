package com.example.banking.application.service;

import com.example.banking.adapter.out.external.bank.BankAccount;
import com.example.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.example.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.example.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.example.banking.application.port.in.RegisterBankAccountCommand;
import com.example.banking.application.port.in.RegisterBankAccountUseCase;
import com.example.banking.application.port.out.RegisterBankAccountPort;
import com.example.banking.application.port.out.RequestBankAccountPort;
import com.example.banking.domain.RegisteredBankAccount;
import com.example.common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {
    private final RegisterBankAccountPort bankAccountPort;
    private final RegisteredBankAccountMapper bankAccountMapper;
    private final RequestBankAccountPort requestBankAccountPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        // 1. 등록된 계좌인지 확인한다.

        BankAccount accountInfo = requestBankAccountPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean accountIsValid = accountInfo.isValid();
        // 2. 등록가능한 계좌라면, 등록한다. 성공하면 등록에 성공한 등록정보를 리턴
        // 2-1. 등록가능하지 않은 계좌라면, 에러를 리턴
        if (accountIsValid) {
            RegisteredBankAccountJpaEntity jpaEntity = bankAccountPort.registerBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid())
            );
            return bankAccountMapper.mapToDomainEntity(jpaEntity);
        } else {
            throw new IllegalArgumentException();
        }

    }
}
