package com.example.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {

    private final String registeredBankAccountId;
    private final Long membershipId;
    private final String bankName;
    private final String bankAccountNumber;
    private final boolean linkedStatusIsValid;
    private final String aggregateIdentifier;


    public static RegisteredBankAccount generateRegsiterBankAccount(
            RegisterBankAccountId registerBankAccountId,
            MembershipId membershipId,
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            LinkedStatusIsValid linkedStatusIsValid,
            AggregateIdentifier aggregateIdentifier

    ) {
        return new RegisteredBankAccount(
                registerBankAccountId.registerBankAccountId,
                membershipId.membershipId,
                bankName.bankName,
                bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid.linkedStatusIsValid,
                aggregateIdentifier.aggregateIdentifier
                );
    }

    public record RegisterBankAccountId(String registerBankAccountId) {
    }

    public record MembershipId(Long membershipId) {
    }

    public record BankName(String bankName) {

    }

    public record BankAccountNumber(String bankAccountNumber) {

    }

    public record LinkedStatusIsValid(boolean linkedStatusIsValid) {

    }

    public record AggregateIdentifier(String aggregateIdentifier) {

    }

}
