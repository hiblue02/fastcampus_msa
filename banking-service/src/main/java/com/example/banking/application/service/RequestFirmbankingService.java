package com.example.banking.application.service;

import com.example.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.example.banking.adapter.out.external.bank.FirmbankingResult;
import com.example.banking.adapter.out.persistence.RequestFirmbankingJpaEntity;
import com.example.banking.adapter.out.persistence.RequestFirmbankingMapper;
import com.example.banking.application.port.in.RequestFirmbankingCommand;
import com.example.banking.application.port.in.RequestFirmbankingUseCase;
import com.example.banking.application.port.out.RequestExternalFirmbanking;
import com.example.banking.application.port.out.RequestFirmbankingPort;
import com.example.banking.domain.RequestFirmbanking;
import com.example.common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmbankingService implements RequestFirmbankingUseCase {
    private final RequestFirmbankingMapper mapper;
    private final RequestFirmbankingPort port;
    private final RequestExternalFirmbanking requestExternalFirmbanking;

    @Override
    public RequestFirmbanking requestFirmbanking(RequestFirmbankingCommand command) {

        // 1. 펌뱅킹 요청상태로 저장
        RequestFirmbankingJpaEntity requestEntity = port.createFirmbankingRequest(
                new RequestFirmbanking.FromBankName(command.getFromBankName()),
                new RequestFirmbanking.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new RequestFirmbanking.ToBankName(command.getToBankName()),
                new RequestFirmbanking.ToBankAccountNumber(command.getToBankAccountNumber()),
                new RequestFirmbanking.MoneyAmount(command.getMoneyAmount()),
                new RequestFirmbanking.FirmbankingStatus(0)
        );

        // 2. 펌뱅킹 요청
        FirmbankingResult firmbankingResult = requestExternalFirmbanking.requestExternalFirmbanking(
                new ExternalFirmbankingRequest(
                        command.getFromBankName(),
                        command.getFromBankAccountNumber(),
                        command.getToBankName(),
                        command.getToBankAccountNumber(),
                        command.getMoneyAmount()
                )
        );
        // 3. 결과에 따라서 1번에 작성했던 FirmbankingRequest 정보를 update
        UUID randomUUID = UUID.randomUUID();
        requestEntity.setUuid(randomUUID);

        if (firmbankingResult.getResultCode() == 0) {
            // 성공
            requestEntity.setFirmbankingStatus(1);
        } else {
            requestEntity.setFirmbankingStatus(2);
        }

        // 4. 결과 저장.
        return mapper.mapToDomainEntity(port.modifyFirmbankingRequest(requestEntity),  randomUUID);
    }
}
