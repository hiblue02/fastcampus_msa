package com.example.banking.adapter.out.persistence;

import com.example.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.example.banking.application.port.out.GetRegisteredBankAccountPort;
import com.example.banking.application.port.out.RegisterBankAccountPort;
import com.example.banking.domain.RegisteredBankAccount;
import com.example.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisterBankAccountPersistenceAdapter implements RegisterBankAccountPort, GetRegisteredBankAccountPort {

    private final SpringDataRegisteredBankAccountRepository repository;

    @Override
    public RegisteredBankAccountJpaEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid,
            RegisteredBankAccount.AggregateIdentifier aggregateIdentifier) {
        return repository.save(
                new RegisteredBankAccountJpaEntity(
                        membershipId.membershipId(),
                        bankName.bankName(),
                        bankAccountNumber.bankAccountNumber(),
                        linkedStatusIsValid.linkedStatusIsValid(),
                        aggregateIdentifier.aggregateIdentifier()
                )
        );
    }

    @Override
    public RegisteredBankAccountJpaEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        return repository.findByMembershipId(command.getMembershipId());
    }
}
