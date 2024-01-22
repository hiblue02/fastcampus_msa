package com.example.banking.application.port.out;

import com.example.banking.adapter.out.persistence.RequestFirmbankingJpaEntity;
import com.example.banking.domain.RequestFirmbanking;

public interface RequestFirmbankingPort {
    RequestFirmbankingJpaEntity createFirmbankingRequest(
            RequestFirmbanking.FromBankName fromBankName,
            RequestFirmbanking.FromBankAccountNumber fromBankAccountNumber,
            RequestFirmbanking.ToBankName toBankName,
            RequestFirmbanking.ToBankAccountNumber toBankAccountNumber,
            RequestFirmbanking.MoneyAmount moneyAmount,
            RequestFirmbanking.FirmbankingStatus firmbankingStatus
    );

    RequestFirmbankingJpaEntity modifyFirmbankingRequest(
           RequestFirmbankingJpaEntity entity
    );
}
