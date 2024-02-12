package com.example.banking.application.service;

import com.example.banking.adapter.axon.command.CreateFirmbankingRequestCommand;
import com.example.banking.adapter.axon.command.UpdateFirmbankingRequestCommand;
import com.example.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.example.banking.adapter.out.external.bank.FirmbankingResult;
import com.example.banking.adapter.out.persistence.RequestFirmbankingJpaEntity;
import com.example.banking.adapter.out.persistence.RequestFirmbankingMapper;
import com.example.banking.application.port.in.RequestFirmbankingCommand;
import com.example.banking.application.port.in.RequestFirmbankingUseCase;
import com.example.banking.application.port.in.UpdateFirmbankingCommand;
import com.example.banking.application.port.in.UpdateFirmbankingUseCase;
import com.example.banking.application.port.out.RequestExternalFirmbankingPort;
import com.example.banking.application.port.out.RequestFirmbankingPort;
import com.example.banking.domain.RequestFirmbanking;
import com.example.common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmbankingService implements RequestFirmbankingUseCase, UpdateFirmbankingUseCase {
    private final RequestFirmbankingMapper mapper;
    private final RequestFirmbankingPort port;
    private final CommandGateway commandGateway;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;

    @Override
    public RequestFirmbanking requestFirmbanking(RequestFirmbankingCommand command) {

        // 1. 펌뱅킹 요청상태로 저장
        RequestFirmbankingJpaEntity requestEntity = port.createFirmbankingRequest(
                new RequestFirmbanking.FromBankName(command.getFromBankName()),
                new RequestFirmbanking.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new RequestFirmbanking.ToBankName(command.getToBankName()),
                new RequestFirmbanking.ToBankAccountNumber(command.getToBankAccountNumber()),
                new RequestFirmbanking.MoneyAmount(command.getMoneyAmount()),
                new RequestFirmbanking.FirmbankingStatus(0),
                new RequestFirmbanking.FirmbankingAggregateIdentifier("")
        );

        // 2. 펌뱅킹 요청
        FirmbankingResult firmbankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(
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

    @Override
    public void requestFirmbankingByEvent(RequestFirmbankingCommand command) {
        CreateFirmbankingRequestCommand createFirmbankingRequestCommand = CreateFirmbankingRequestCommand.builder()
                .fromBankName(command.getFromBankName())
                .fromBankAccountNumber(command.getFromBankAccountNumber())
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .moneyAmount(command.getMoneyAmount())
                .build();

        commandGateway.send(createFirmbankingRequestCommand)
                .whenComplete((result, throwable)->{
                    if (throwable != null) {
                        throwable.printStackTrace();
                    } else {
                        RequestFirmbankingJpaEntity requestedEntity = port.createFirmbankingRequest(
                                new RequestFirmbanking.FromBankName(command.getFromBankName()),
                                new RequestFirmbanking.FromBankAccountNumber(command.getFromBankAccountNumber()),
                                new RequestFirmbanking.ToBankName(command.getToBankName()),
                                new RequestFirmbanking.ToBankAccountNumber(command.getToBankAccountNumber()),
                                new RequestFirmbanking.MoneyAmount(command.getMoneyAmount()),
                                new RequestFirmbanking.FirmbankingStatus(0),
                                new RequestFirmbanking.FirmbankingAggregateIdentifier(result.toString())
                        );

                        FirmbankingResult firmbankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(
                                new ExternalFirmbankingRequest(
                                        command.getFromBankName(),
                                        command.getFromBankAccountNumber(),
                                        command.getToBankName(),
                                        command.getToBankAccountNumber(),
                                        command.getMoneyAmount()
                                )
                        );
                        if(firmbankingResult.getResultCode() == 0) {
                            requestedEntity.setFirmbankingStatus(1);
                        } else {
                            requestedEntity.setFirmbankingStatus(2);
                        }

                        port.modifyFirmbankingRequest(requestedEntity);
                    }
                });
    }

    @Override
    public void updateFirmbankingByEvent(UpdateFirmbankingCommand command) {
        UpdateFirmbankingRequestCommand updateFirmbankingRequestCommand = new UpdateFirmbankingRequestCommand(command.getFirmbankingAggregateIdentifier(), command.getFirmbankingStatus());
        commandGateway.send(updateFirmbankingRequestCommand)
                .whenComplete((result,throwable) -> {
                   if(throwable != null) {
                       throwable.printStackTrace();
                   } else {
                       RequestFirmbankingJpaEntity entity = port.getFirmbankingRequest(
                               new RequestFirmbanking.FirmbankingAggregateIdentifier(command.getFirmbankingAggregateIdentifier())
                       );
                       // status 의 변경으로 인한 외부 은행과의 커뮤니케이션
                       // if rollback -> 0, status 변경도 해주겠지만
                       // + 기존 펌뱅킹 정보에서 from <-> to 가 변경된 펌뱅킹을 요청하는 새로운 요청
                       entity.setFirmbankingStatus(command.getFirmbankingStatus());
                       port.modifyFirmbankingRequest(entity);
                   }
                });

    }
}
