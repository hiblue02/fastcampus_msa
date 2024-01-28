package com.example.money.domain;

import lombok.*;

import java.util.Arrays;
import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {
    @Getter private final String moneyChangingRequestId;
    @Getter private final String targetMembershipId;
    @Getter private final ChangingType changingType;
    @Getter private final int changingMoneyAmount;
    @Getter private final ChangingMoneyStatus changingMoneyStatus;
    @Getter private final String uuid;
    @Getter private final Date createdAt;


    public static MoneyChangingRequest generateMoneyChangingRequest(
        MoneyChangingRequestId moneyChangingRequestId,
        TargetMembershipId targetMembershipId,
        ChangingTypeCode changingTypeCode,
        ChangingMoneyAmount changingMoneyAmount,
        ChangingMoneyStatusCode changingMoneyStatusCode,
        Uuid uuid,
        CreatedAt createdAt
    ) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.moneyChangingRequestId,
                targetMembershipId.targetMembershipId,
                changingTypeCode.changingType,
                changingMoneyAmount.changingMoneyAmount,
                changingMoneyStatusCode.changingMoneyStatus,
                uuid.uuid,
                createdAt.createdAt
        );
    }

    @Value
    public static class MoneyChangingRequestId {
        String moneyChangingRequestId;

        public MoneyChangingRequestId(String value) {
            this.moneyChangingRequestId = value;
        }
    }

    @Value
    public static class TargetMembershipId {
        String targetMembershipId;

        public TargetMembershipId(String targetMembershipId) {
            this.targetMembershipId = targetMembershipId;
        }
    }

    @Value
    public static class ChangingTypeCode {
        ChangingType changingType;

        public ChangingTypeCode(ChangingType changingType) {
            this.changingType = changingType;
        }
    }

    @Value
    public static class ChangingMoneyAmount {
        int changingMoneyAmount;

        public ChangingMoneyAmount(int changingMoneyAmount) {
            this.changingMoneyAmount = changingMoneyAmount;
        }
    }

    @Value
    public static class ChangingMoneyStatusCode {
        ChangingMoneyStatus changingMoneyStatus;

        public ChangingMoneyStatusCode(ChangingMoneyStatus changingMoneyStatus) {
            this.changingMoneyStatus = changingMoneyStatus;
        }
    }

    @Value
    public static class Uuid {
        String uuid;

        public Uuid(String uuid) {
            this.uuid = uuid;
        }
    }

    @Value
    public static class CreatedAt {
        Date createdAt;

        public CreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum ChangingMoneyStatus {
        REQUESTED(0),
        SUCCEEDED(1),
        FAILED(2),
        CANCELED(3);

        private final int value;

        public static ChangingMoneyStatus getCode(int value) {
            return Arrays.stream(values())
                    .filter(code -> code.getValue() == value)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }

        public boolean isSuccess() {
            return this == SUCCEEDED;
        }

        public boolean isFail() {
            return this != FAILED;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum ChangingType {
        INCREASING(0),
        DECREASING(1);

        private final int value;
        public boolean isIncrease() {
            return this == INCREASING;
        }

        public boolean isDecrease() {
            return this == DECREASING;
        }

        public static ChangingType getCode(int value) {
            return Arrays.stream(values())
                    .filter(code -> code.getValue() == value)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }

    }
}
