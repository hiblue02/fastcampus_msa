package com.example.banking.application.port.out;

import com.example.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.example.banking.application.port.in.GetRegisteredBankAccountCommand;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountJpaEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
