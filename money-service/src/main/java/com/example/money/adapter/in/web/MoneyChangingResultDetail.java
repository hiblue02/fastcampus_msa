package com.example.money.adapter.in.web;

import com.example.money.domain.constants.ChangingMoneyStatus;
import com.example.money.domain.constants.ChangingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingResultDetail {
    private String targetMembershipId;
    private int amount;
    private MoneyChangingType moneyChangingType;
    private MoneyChangingResultStatus moneyChangingResultStatus;

    enum MoneyChangingType {
        INCREASING,
        DECREASING;

        public ChangingType toDomainCode() {
            if (this.isIncrease()) {
                return ChangingType.INCREASING;
            }
            if (this.isDecrease()) {
                return ChangingType.DECREASING;
            }
            throw new IllegalArgumentException();
        }

        public boolean isIncrease() {
            return this == INCREASING;
        }

        public boolean isDecrease() {
            return this == DECREASING;
        }

        public static MoneyChangingType of(ChangingType domainCode) {
            if (domainCode.isIncrease()) {
                return INCREASING;
            }
            if (domainCode.isDecrease()) {
                return DECREASING;
            }
            throw new IllegalArgumentException();
        }
    }

        enum MoneyChangingResultStatus {
            SUCCEEDED,
            FAILED,
            FAILED_NOT_ENOUGH_MONEY;

            public boolean isSuccess() {
                return this == SUCCEEDED;
            }

            public  boolean isFail() {
                return this != SUCCEEDED;
            }

            public ChangingMoneyStatus toDomainCode() {
                if (this.isSuccess()) {
                    return ChangingMoneyStatus.SUCCEEDED;
                }
                if (this.isFail()) {
                    return ChangingMoneyStatus.FAILED;
                }
                throw new IllegalArgumentException();
            }

            public static MoneyChangingResultStatus of(ChangingMoneyStatus domainCode) {
                if (domainCode.isSuccess()) {
                    return SUCCEEDED;
                }
                if (domainCode.isFail()) {
                    return FAILED;
                }
                throw new IllegalArgumentException();
            }
        }

}
