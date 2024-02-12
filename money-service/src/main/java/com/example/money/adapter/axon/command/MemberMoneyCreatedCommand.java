package com.example.money.adapter.axon.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyCreatedCommand {
    @NotNull
    private String membershipId;

    public MemberMoneyCreatedCommand(String membershipId) {
        this.membershipId = membershipId;
    }

}
