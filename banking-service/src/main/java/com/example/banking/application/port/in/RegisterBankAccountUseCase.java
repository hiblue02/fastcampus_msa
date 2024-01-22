package com.example.banking.application.port.in;

import com.example.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}
