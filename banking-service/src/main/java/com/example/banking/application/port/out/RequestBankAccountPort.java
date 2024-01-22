package com.example.banking.application.port.out;

import com.example.banking.adapter.out.external.bank.BankAccount;
import com.example.banking.adapter.out.external.bank.GetBankAccountRequest;

public interface RequestBankAccountPort {
   BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
