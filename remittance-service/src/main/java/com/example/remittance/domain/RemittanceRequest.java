package com.example.remittance.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RemittanceRequest {
    private final String remittanceRequestId;
    private final String remittanceFromMembershipId;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final String remittanceType;
    private final int amount;
    private final String remittanceStatus;

    public record RemittanceRequestId(String remittanceRequestId) {
    }

    public record RemittanceFromMembershipId(String remittanceFromMembershipId) {
    }

    public record ToBankName(String toBankName) {
    }

    public record ToBankAccountNumber(String toBankAccountNumber) {
    }

    public record RemittanceType(String remittanceType) {}
    public record Amount(int amount) {
    }

    public record RemittanceStatus(String remittanceStatus) {
    }

    public static RemittanceRequest generateRemittanceRequest(
      RemittanceRequest.RemittanceRequestId remittanceRequestId,
      RemittanceRequest.RemittanceFromMembershipId remittanceFromMembershipId,
      RemittanceRequest.ToBankName toBankName,
      RemittanceRequest.ToBankAccountNumber toBankAccountNumber,
      RemittanceRequest.RemittanceType remittanceType,
      RemittanceRequest.Amount amount,
      RemittanceRequest.RemittanceStatus remittanceStatus
    ){
        return new RemittanceRequest(
                remittanceRequestId.remittanceRequestId,
                remittanceFromMembershipId.remittanceFromMembershipId,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                remittanceType.remittanceType,
                amount.amount,
                remittanceStatus.remittanceStatus
        );
    }

}
