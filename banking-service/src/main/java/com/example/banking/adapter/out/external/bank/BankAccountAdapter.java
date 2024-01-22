package com.example.banking.adapter.out.external.bank;

import com.example.banking.application.port.out.RequestBankAccountPort;
import com.example.banking.application.port.out.RequestExternalFirmbanking;
import com.example.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountPort, RequestExternalFirmbanking {

    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
        return new BankAccount(request.getBankName(), request.getBankAccountNumber());
    }

    @Override
    public FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request) {
        // 실제로 외부 은행에 http 통신을 통해서 펌뱅킹 요청
        // 결과를 Firmbanking Result로 파싱
        return new FirmbankingResult(0);
        }
}
