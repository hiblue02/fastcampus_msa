package com.example.remittance.adapter.out.service.banking;

import com.example.common.CommonHttpClient;
import com.example.common.ExternalSystemAdapter;
import com.example.remittance.application.port.out.banking.BankingInfo;
import com.example.remittance.application.port.out.banking.BankingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceAdapter implements BankingPort {
    private final CommonHttpClient bankingServiceHttpClient;
    @Value("${service.banking.url}")
    private String bankingServiceEndPoint;
    @Override
    public BankingInfo getMembershipBankingInfo(String bankName, String BankAccountNumber) {
        return null;
    }

    @Override
    public boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount) {
        return false;
    }
}
