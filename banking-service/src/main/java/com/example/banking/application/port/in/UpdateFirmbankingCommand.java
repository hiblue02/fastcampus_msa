package com.example.banking.application.port.in;

import com.example.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = false)
@Data
public class UpdateFirmbankingCommand extends SelfValidating<UpdateFirmbankingCommand> {
    private final String firmbankingAggregateIdentifier;
    private final int firmbankingStatus;
}
