package com.example.banking.adapter.out.external.bank;

import lombok.Getter;

@Getter
public class BankAccount {
    private String bankName;
    private String bankAccountNumber;

    private boolean isValid;

    public BankAccount(String bankName, String bankAccountNumber) {
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.isValid = true;
    }
}
