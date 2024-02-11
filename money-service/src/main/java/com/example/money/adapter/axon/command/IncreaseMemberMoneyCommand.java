package com.example.money.adapter.axon.command;

import com.example.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMemberMoneyCommand extends SelfValidating<IncreaseMemberMoneyCommand> {
    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier; // command의 타겟 대상의 식별자 Field or method level annotation that marks a field or method providing the identifier of the aggregate that a command targets.
    @NotNull
    private final String membershipId;
    @NotNull
    private final int amount;

    public IncreaseMemberMoneyCommand(String aggregateIdentifier, String membershipId, int amount) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.membershipId = membershipId;
        this.amount = amount;
        this.validateSelf();
    }
}
