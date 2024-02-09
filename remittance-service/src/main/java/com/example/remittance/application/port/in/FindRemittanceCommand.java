package com.example.remittance.application.port.in;

import com.example.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class FindRemittanceCommand extends SelfValidating<FindRemittanceCommand> {
    @NotNull
    private String membershipId;

    public FindRemittanceCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
