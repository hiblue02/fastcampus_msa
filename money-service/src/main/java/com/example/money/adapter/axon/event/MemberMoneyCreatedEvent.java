package com.example.money.adapter.axon.event;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MemberMoneyCreatedEvent  {

    @NotNull
    private String membershipId;

    public MemberMoneyCreatedEvent(@NotNull String membershipID) {
        this.membershipId = membershipID;
    }

}
