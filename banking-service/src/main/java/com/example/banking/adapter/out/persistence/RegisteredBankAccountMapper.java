package com.example.banking.adapter.out.persistence;

import com.example.banking.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {

    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity jpaEntity) {
        return RegisteredBankAccount.generateRegsiterBankAccount(
                new RegisteredBankAccount.RegisterBankAccountId(jpaEntity.getRegisteredBankAccountId().toString()),
                new RegisteredBankAccount.MembershipId(jpaEntity.getMembershipId()),
                new RegisteredBankAccount.BankName(jpaEntity.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(jpaEntity.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(jpaEntity.isLinkedStatusIsValid()),
                new RegisteredBankAccount.AggregateIdentifier(jpaEntity.getAggregateIdentifier())
        );
    }

}
