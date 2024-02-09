package com.example.application.port.in;

import com.example.common.SelfValidating;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
public class FindRemittanceCommand extends SelfValidating<FindRemittanceCommand> {
    @NotNull
    private String membershipId;

    public FindRemittanceCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
