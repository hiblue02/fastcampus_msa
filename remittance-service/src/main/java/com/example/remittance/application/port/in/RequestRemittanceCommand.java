package com.example.remittance.application.port.in;

import com.example.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {
    @NotNull
    private String fromMembershipId;
    private String toMembershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int remittanceType;

    @NotNull
    @NotBlank
    private int amount;

    public RequestRemittanceCommand(
            String fromMembershipId,
            String toMembershipId,
            String toBankName,
            String toBankAccountNumber,
            int remittanceType,
            int amount
    ) {
        this.fromMembershipId = fromMembershipId;
        this.toMembershipId = toMembershipId;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.remittanceType = remittanceType;
        this.amount = amount;
        this.validateSelf();
    }
}
