package com.example.banking.adapter.axon.aggregate;

import com.example.banking.adapter.axon.command.CreateFirmbankingRequestCommand;
import com.example.banking.adapter.axon.command.UpdateFirmbankingRequestCommand;
import com.example.banking.adapter.axon.event.FirmbankingRequestCreatedEvent;
import com.example.banking.adapter.axon.event.FirmbankingRequestUpdatedEvent;
import com.example.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.example.banking.adapter.out.external.bank.FirmbankingResult;
import com.example.banking.application.port.out.RequestExternalFirmbankingPort;
import com.example.banking.application.port.out.RequestFirmbankingPort;
import com.example.banking.domain.RequestFirmbanking;
import com.example.common.command.RequestFirmbankingCommand;
import com.example.common.command.RollbackFirmbankingRequestCommand;
import com.example.common.event.RequestFirmbankingFinishedEvent;
import com.example.common.event.RollbackFirmbankingFinishedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@NoArgsConstructor
public class FirmbankingRequestAggregate {
    @AggregateIdentifier
    private String id;

    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;
    private int firmbankingStatus;

    @CommandHandler
    public FirmbankingRequestAggregate(CreateFirmbankingRequestCommand command) {
        apply(new FirmbankingRequestCreatedEvent(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()));
    }

    /**
     * 펌뱅킹 성공한 경우
     * @param command
     * @param port
     * @param externalPort
     */
    @CommandHandler
    public FirmbankingRequestAggregate(RequestFirmbankingCommand command,
                                       RequestFirmbankingPort port,
                                       RequestExternalFirmbankingPort externalPort) {

        // 1. 펌뱅킹 요청 저장 (고객계좌 > 법인계좌)
        port.createFirmbankingRequest(
                new RequestFirmbanking.FromBankName(command.getFromBankName()),
                new RequestFirmbanking.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new RequestFirmbanking.ToBankName("fastcampus-bank"),
                new RequestFirmbanking.ToBankAccountNumber("123-333-9999"),
                new RequestFirmbanking.MoneyAmount(command.getMoneyAmount()),
                new RequestFirmbanking.FirmbankingStatus(0),
                new RequestFirmbanking.FirmbankingAggregateIdentifier(id));

        // 2. 펌뱅킹 수행
        FirmbankingResult firmbankingResult = externalPort.requestExternalFirmbanking(
                new ExternalFirmbankingRequest(
                        command.getFromBankName(),
                        command.getFromBankAccountNumber(),
                        command.getToBankName(),
                        command.getToBankAccountNumber(),
                        command.getMoneyAmount()
                )
        );
        // 3. 펌뱅킹 결과 발행
        apply(new RequestFirmbankingFinishedEvent(
                command.getRequestFirmbankingId(),
                command.getRechargeRequestId(),
                command.getMembershipId(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount(),
                firmbankingResult.getResultCode(),
                UUID.randomUUID().toString()
        ));
    }


    /**
     * 펌뱅킹 실패한 경우
     * @param command
     * @param firmbankingPort
     * @param externalFirmbankingPort
     */
    @CommandHandler
    public FirmbankingRequestAggregate(@NotNull RollbackFirmbankingRequestCommand command,
                                       RequestFirmbankingPort firmbankingPort,
                                       RequestExternalFirmbankingPort externalFirmbankingPort) {

        // rollback 수행 (법인 계좌 > 고객 계좌 펌뱅킹)
        firmbankingPort.createFirmbankingRequest(
                new RequestFirmbanking.FromBankName("fastcampus"),
                new RequestFirmbanking.FromBankAccountNumber("123-333-9999"),
                new RequestFirmbanking.ToBankName(command.getBankName()),
                new RequestFirmbanking.ToBankAccountNumber(command.getBankAccountNumber()),
                new RequestFirmbanking.MoneyAmount(command.getMoneyAmount()),
                new RequestFirmbanking.FirmbankingStatus(0),
                new RequestFirmbanking.FirmbankingAggregateIdentifier(id));

        // firmbanking!
        FirmbankingResult result = externalFirmbankingPort.requestExternalFirmbanking(
                new ExternalFirmbankingRequest(
                        "fastcampus",
                        "123-333-9999",
                        command.getBankName(),
                        command.getBankAccountNumber(),
                        command.getMoneyAmount()
                ));


        apply(new RollbackFirmbankingFinishedEvent(
                command.getRollbackFirmbankingId(),
                command.getMembershipId(),
                UUID.randomUUID().toString())
        );
    }



    @CommandHandler
    public String handle(UpdateFirmbankingRequestCommand command) {
        apply(new FirmbankingRequestUpdatedEvent(command.getFirmbankingStatus()));
        return command.getAggregateIdentifier();
    }

    @EventSourcingHandler
    public void on(FirmbankingRequestCreatedEvent event) {
        this.id = UUID.randomUUID().toString();
        this.fromBankName = event.getFromBankName();
        this.fromBankAccountNumber = event.getFromBankAccountNumber();
        this.toBankName = event.getToBankName();
        this.toBankAccountNumber = event.getToBankAccountNumber();
    }

}
