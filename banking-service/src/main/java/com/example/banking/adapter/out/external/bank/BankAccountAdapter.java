package com.example.banking.adapter.out.external.bank;

import com.example.banking.application.port.out.RequestBankAccountPort;
import com.example.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountPort {

    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
        return new BankAccount(request.getBankName(), request.getBankAccountNumber());
    }
}
