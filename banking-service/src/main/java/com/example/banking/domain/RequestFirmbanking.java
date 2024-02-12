package com.example.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestFirmbanking {
    private final String requestFirmbankingId;
    private final String fromBankName;
    private final String fromBankAccountNumber;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final int moneyAmount;
    private final int firmbankingStatus;
    private final UUID uuid;
    private final String aggregateIdentifier;

    public static RequestFirmbanking generateFirmbankingRequest(
            RequestFirmbankingId requestFirmbankingId,
            FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            MoneyAmount moneyAmount,
            FirmbankingStatus firmbankingStatus,
            UUID uuid,
            FirmbankingAggregateIdentifier identifier) {
        return new RequestFirmbanking(
                requestFirmbankingId.requestFirmbankingId,
                fromBankName.fromBankName,
                fromBankAccountNumber.fromBankAccountNumber,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                moneyAmount.moneyAmount,
                firmbankingStatus.firmbankingStatus,
                uuid,
                identifier.aggregateIdentifier
        );
    }

    @Value
    public static class RequestFirmbankingId {
        String requestFirmbankingId;

        public RequestFirmbankingId(String value) {
            this.requestFirmbankingId = value;
        }
    }

    @Value
    public static class FromBankName {
        String fromBankName;

        public FromBankName(String value) {
            this.fromBankName = value;
        }
    }

    @Value
    public static class FromBankAccountNumber {
        String fromBankAccountNumber;

        public FromBankAccountNumber(String fromBankAccountNumber) {
            this.fromBankAccountNumber = fromBankAccountNumber;
        }
    }

    @Value
    public static class ToBankName {
        String toBankName;

        public ToBankName(String toBankName) {
            this.toBankName = toBankName;
        }
    }

    @Value
    public static class ToBankAccountNumber {
        String toBankAccountNumber;

        public ToBankAccountNumber(String toBankAccountNumber) {
            this.toBankAccountNumber = toBankAccountNumber;
        }
    }

    @Value
    public static class MoneyAmount {
        int moneyAmount;

        public MoneyAmount(int moneyAmount) {
            this.moneyAmount = moneyAmount;
        }
    }

    @Value
    public static class FirmbankingStatus {
        int firmbankingStatus;

        public FirmbankingStatus(int firmbankingStatus) {
            this.firmbankingStatus = firmbankingStatus;
        }
    }

    public record FirmbankingAggregateIdentifier(
            String aggregateIdentifier
    ) {
    }
}
