package com.example.banking.adapter.axon.aggregate;

import com.example.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.example.banking.adapter.axon.event.CreateRegisteredBankAccountEvent;
import com.example.banking.adapter.out.external.bank.BankAccount;
import com.example.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.example.banking.application.port.out.RequestBankAccountPort;
import com.example.common.command.CheckRegisteredBankAccountCommand;
import com.example.common.event.CheckedRegisteredBankAccountEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class RegisteredBankAccountAggregate {
    @AggregateIdentifier
    private String id;

    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    @CommandHandler
    public RegisteredBankAccountAggregate(CreateRegisteredBankAccountCommand command) {
        apply(new CreateRegisteredBankAccountCommand(
                command.getMembershipId(),
                command.getBankName(),
                command.getBankAccountNumber()
        ));
    }

    @CommandHandler
    public void handle(CheckRegisteredBankAccountCommand command, RequestBankAccountPort requestBankAccountPort) {
        this.id = command.getAggregateIdentifier();
        BankAccount bankAccountInfo = requestBankAccountPort.getBankAccountInfo(
                new GetBankAccountRequest(
                        command.getBankName(),
                        command.getBankAccountNumber())
        );

        apply(new CheckedRegisteredBankAccountEvent(
                command.getRechargeRequestId(),
                command.getCheckRegisteredBankAccountId(),
                command.getMembershipId(),
                bankAccountInfo.isValid(),
                command.getAmount(),
                UUID.randomUUID().toString(),
                bankAccountInfo.getBankName(),
                bankAccountInfo.getBankAccountNumber()
        ));

    }

    @EventSourcingHandler
    public void on (CreateRegisteredBankAccountEvent event) {
        this.id = UUID.randomUUID().toString();
        this.bankAccountNumber = event.getBankAccountNumber();
        this.bankName = event.getBankName();
        this.membershipId = event.getMembershipId();
    }
}
