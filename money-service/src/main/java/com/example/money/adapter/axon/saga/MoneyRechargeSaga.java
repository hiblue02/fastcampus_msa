package com.example.money.adapter.axon.saga;

import com.example.common.command.CheckRegisteredBankAccountCommand;
import com.example.common.command.RequestFirmbankingCommand;
import com.example.common.command.RollbackFirmbankingRequestCommand;
import com.example.common.event.CheckedRegisteredBankAccountEvent;
import com.example.common.event.RequestFirmbankingFinishedEvent;
import com.example.common.event.RollbackFirmbankingFinishedEvent;
import com.example.money.adapter.axon.event.RechargingRequestCreatedEvent;
import com.example.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.example.money.application.port.out.IncreaseMoneyPort;
import com.example.money.domain.MemberMoney;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
@Saga
public class MoneyRechargeSaga {
    @NonNull
    private transient CommandGateway commandGateway;

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    /**
     *  1.계좌 등록 여부 확인하기
     *  CheckRegisteredBankAccountCommand > Check Bank Account > axon server > Banking Service > Common
     * @param event
     */
    @StartSaga
    @SagaEventHandler(associationProperty = "rechargingRequestId")
    public void handle(RechargingRequestCreatedEvent event) {
        String checkRegisteredBankAccountId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("checkRegisteredBankAccountId", checkRegisteredBankAccountId);
        commandGateway.send(
                new CheckRegisteredBankAccountCommand(
                        event.getRegisteredBankAccountAggregateIdentifier(),
                        event.getRechargingRequestId(),
                        event.getMembershipId(),
                        checkRegisteredBankAccountId,
                        event.getBankName(),
                        event.getBankAccountNumber(),
                        event.getAmount()
                )
        ).whenComplete(
                (result, throwable) -> {
                    if(throwable != null) {
                        log.error(throwable.getMessage());
                    } else {
                        log.info("success");
                    }
                }
        );

    }

    // 2. 계좌 등록 여부 결과 확인 후 성공이면 송금(고객계좌 > 법인계좌) 요청하기
    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    public void handle(CheckedRegisteredBankAccountEvent event) {
        log.info("CheckedRegisteredBankAccountEvent saga: " + event.toString());
        if (event.isChecked()) {
            log.info("CheckedRegisteredBankAccountEvent event success");
        } else {
            log.error("CheckedRegisteredBankAccountEvent event Failed");
        }

        String requestFirmbankingId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("requestFirmbankingId", requestFirmbankingId);

        // 송금 요청
        // 고객 계좌 -> 법인 계좌
        commandGateway.send(new RequestFirmbankingCommand(
                requestFirmbankingId,
                event.getFirmbankingRequestAggregateIdentifier()
                , event.getRechargingRequestId()
                , event.getMembershipId()
                , event.getFromBankName()
                , event.getFromBankAccountNumber()
                , "fastcampus"
                , "123456789"
                , event.getAmount()
        )).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        log.error("RequestFirmbankingCommand Command failed, {}", throwable.getMessage());
                    } else {
                        log.info("RequestFirmbankingCommand Command success");
                    }
                }
        );
    }

    /**
     * 3. 송금 결과 확인하고 성공이면 머니 잔액 증가
     * 3-1. 머니 잔액 증가하다가 실패하면 펌뱅킹 Rollback 요청하기.
     * 3-2. 머니 잔액 증가 성공하면 saga 종료.
     * @param event
     * @param increaseMoneyPort
     */
    @SagaEventHandler(associationProperty = "requestFirmbankingId")
    public void handle(RequestFirmbankingFinishedEvent event, IncreaseMoneyPort increaseMoneyPort) {
        log.info("RequestFirmbankingFinishedEvent saga: " + event.toString());
        boolean status = event.getStatus() == 0;
        if (status) {
           log.info("RequestFirmbankingFinishedEvent event success");
        } else {
            log.error("RequestFirmbankingFinishedEvent event Failed");
        }

        // DB Update 명령.
        MemberMoneyJpaEntity resultEntity =
                increaseMoneyPort.increaseMoney(
                        new MemberMoney.MembershipId(event.getMembershipId())
                        , event.getMoneyAmount()
                );

        if (resultEntity == null) {
            // 실패 시, 롤백 이벤트
            String rollbackFirmbankingId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith("rollbackFirmbankingId", rollbackFirmbankingId);
            commandGateway.send(new RollbackFirmbankingRequestCommand(
                    rollbackFirmbankingId
                    ,event.getRequestFirmbankingAggregateIdentifier()
                    , event.getRechargingRequestId()
                    , event.getMembershipId()
                    , event.getToBankName()
                    , event.getToBankAccountNumber()
                    , event.getMoneyAmount()
            )).whenComplete(
                    (result, throwable) -> {
                        if (throwable != null) {
                            throwable.printStackTrace();
                            System.out.println("RollbackFirmbankingRequestCommand Command failed");
                        } else {
                            System.out.println("Saga success : "+ result.toString());
                            SagaLifecycle.end();
                        }
                    }
            );
        } else {
            // 성공 시, saga 종료.
            SagaLifecycle.end();
        }
    }

    /**
     * rollback 까지 성공하면 종료.
     * @param event
     */
    @EndSaga
    @SagaEventHandler(associationProperty = "rollbackFirmbankingId")
    public void handle(RollbackFirmbankingFinishedEvent event) {
        System.out.println("RollbackFirmbankingFinishedEvent saga: " + event.toString());
    }
}
