package com.example.application.port.out.banking;

public interface BankingPort {
    BankingInfo getMembershipBankingInfo(String bankName, String BankAccountNumber);
    boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount);
}
