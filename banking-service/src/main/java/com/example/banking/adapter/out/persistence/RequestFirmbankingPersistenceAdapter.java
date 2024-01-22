package com.example.banking.adapter.out.persistence;

import com.example.banking.application.port.out.RequestFirmbankingPort;
import com.example.banking.domain.RequestFirmbanking;
import com.example.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class RequestFirmbankingPersistenceAdapter implements RequestFirmbankingPort {

    private final SpringDataRequestFirmbankingRepository repository;

    @Override
    public RequestFirmbankingJpaEntity createFirmbankingRequest(
            RequestFirmbanking.FromBankName fromBankName,
            RequestFirmbanking.FromBankAccountNumber fromBankAccountNumber,
            RequestFirmbanking.ToBankName toBankName,
            RequestFirmbanking.ToBankAccountNumber toBankAccountNumber,
            RequestFirmbanking.MoneyAmount moneyAmount,
            RequestFirmbanking.FirmbankingStatus firmbankingStatus
    ) {
        return repository.save(
                new RequestFirmbankingJpaEntity(
                        fromBankName.getFromBankName(),
                        fromBankAccountNumber.getFromBankAccountNumber(),
                        toBankName.getToBankName(),
                        toBankAccountNumber.getToBankAccountNumber(),
                        moneyAmount.getMoneyAmount(),
                        firmbankingStatus.getFirmbankingStatus(),
                        UUID.randomUUID()
                )
        );
    }

    @Override
    public RequestFirmbankingJpaEntity modifyFirmbankingRequest(RequestFirmbankingJpaEntity entity) {
        return repository.save(entity);
    }
}
