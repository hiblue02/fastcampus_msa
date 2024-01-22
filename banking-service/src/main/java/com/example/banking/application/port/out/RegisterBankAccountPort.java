package com.example.banking.application.port.out;

import com.example.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.example.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {
    RegisteredBankAccountJpaEntity registerBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid
    );
}
