package com.example.banking.adapter.out.persistence;

import com.example.banking.domain.RequestFirmbanking;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestFirmbankingMapper {

    public RequestFirmbanking mapToDomainEntity(RequestFirmbankingJpaEntity jpaEntity, UUID uuid) {
        return RequestFirmbanking.generateFirmbankingRequest(
                new RequestFirmbanking.RequestFirmbankingId(jpaEntity.getRequestFirmbankingId() + ""),
                new RequestFirmbanking.FromBankName(jpaEntity.getFromBankName()),
                new RequestFirmbanking.FromBankAccountNumber(jpaEntity.getFromBankAccountNumber()),
                new RequestFirmbanking.ToBankName(jpaEntity.getToBankName()),
                new RequestFirmbanking.ToBankAccountNumber(jpaEntity.getToBankAccountNumber()),
                new RequestFirmbanking.MoneyAmount(jpaEntity.getMoneyAmount()),
                new RequestFirmbanking.FirmbankingStatus(jpaEntity.getFirmbankingStatus()),
                uuid
        );
    }

}
