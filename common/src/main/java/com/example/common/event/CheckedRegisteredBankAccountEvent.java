package com.example.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckedRegisteredBankAccountEvent {
    private String rechargingRequestId;
    private String checkRegisteredBankAccountId;
    private String membershipId;
    private boolean isChecked;
    private int amount;
    private String firmbankingRequestAggregateIdentifier;
    private String fromBankName;
    private String fromBankAccountNumber;
}
