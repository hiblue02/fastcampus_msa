package com.example.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {

    private final String registeredBankAccountId;
    private final Long membershipId;
    private final String bankName;
    private final String bankAccountNumber;
    private final boolean linkedStatusIsValid;


    public static RegisteredBankAccount generateRegsiterBankAccount(
            RegisterBankAccountId registerBankAccountId,
            MembershipId membershipId,
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            LinkedStatusIsValid linkedStatusIsValid

    ) {
        return new RegisteredBankAccount(
                registerBankAccountId.registerBankAccountId,
                membershipId.membershipId,
                bankName.bankName,
                bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid.linkedStatusIsValid
        );
    }

    @Value
    public static class RegisterBankAccountId {
        public RegisterBankAccountId(String value) {
            this.registerBankAccountId = value;
        }

        String registerBankAccountId;
    }

    @Value
    public static class MembershipId {
        public MembershipId(Long value) {
            this.membershipId = value;
        }

        Long membershipId;
    }

    @Value
    public static class BankName {
        public BankName(String value) {
            this.bankName = value;
        }

        String bankName;
    }

    @Value
    public static class BankAccountNumber {
        public BankAccountNumber(String value) {
            this.bankAccountNumber = value;
        }

        String bankAccountNumber;
    }

    @Value
    public static class LinkedStatusIsValid {
        public LinkedStatusIsValid(boolean value) {
            this.linkedStatusIsValid = value;
        }

        boolean linkedStatusIsValid;
    }

}
