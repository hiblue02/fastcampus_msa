package com.example.banking.adapter.out.persistence;

import com.example.banking.application.port.out.RegisterBankAccountPort;
import com.example.banking.domain.RegisteredBankAccount;
import com.example.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisterBankAccountPersistenceAdapter implements RegisterBankAccountPort {

    private final SpringDataRegisteredBankAccountRepository repository;

    @Override
    public RegisteredBankAccountJpaEntity registerBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid) {
        return repository.save(
                new RegisteredBankAccountJpaEntity(
                        membershipId.getMembershipId(),
                        bankName.getBankName(),
                        bankAccountNumber.getBankAccountNumber(),
                        linkedStatusIsValid.isLinkedStatusIsValid()
                )
        );
    }
}
