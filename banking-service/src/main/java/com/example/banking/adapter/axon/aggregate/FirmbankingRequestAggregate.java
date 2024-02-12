package com.example.banking.adapter.axon.aggregate;

import com.example.banking.adapter.axon.command.CreateFirmbankingRequestCommand;
import com.example.banking.adapter.axon.command.UpdateFirmbankingRequestCommand;
import com.example.banking.adapter.axon.event.FirmbankingRequestCreatedEvent;
import com.example.banking.adapter.axon.event.FirmbankingRequestUpdatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

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
