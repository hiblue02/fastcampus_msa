package com.example.money.adapter.axon.aggregate;

import com.example.money.adapter.axon.command.IncreaseMemberMoneyCommand;
import com.example.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.example.money.adapter.axon.event.IncreaseMemberMoneyEvent;
import com.example.money.adapter.axon.event.MemberMoneyCreatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
@Data
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;

    private Long membershipId;
    private int balance;

    @CommandHandler // annotated constructor, This annotation tells the framework that the given constructor is capable of handling the Command.
    public MemberMoneyAggregate(MemberMoneyCreatedCommand command) {
        apply(new MemberMoneyCreatedEvent(command.getMembershipId()));
    }

    @CommandHandler // annotated functions are the place where you would put your decision-making/business logic.
    public String handle(@NotNull IncreaseMemberMoneyCommand command) {
        id = command.getAggregateIdentifier();

        // store event
        apply(new IncreaseMemberMoneyEvent(id, command.getMembershipId(), command.getAmount()));
        return id;
    }
    @EventSourcingHandler // is what tells the framework that the annotated function should be called when the Aggregate is 'sourced from its events'.
    public void on(MemberMoneyCreatedEvent event) {
        System.out.println("MemberMoneyCreatedEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getMembershipId());
        balance = 0;
    }

    @EventSourcingHandler
    public void on(IncreaseMemberMoneyEvent event) {
        System.out.println("IncreaseMemberMoneyEvent Sourcing Handler");
        id = event.getAggregateIdentifier();
        membershipId = Long.parseLong(event.getTargetMembershipId());
        balance = event.getAmount();
    }

    public MemberMoneyAggregate() {
    }
}
