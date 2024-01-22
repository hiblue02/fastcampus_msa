package com.example.banking.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Table(name = "registered_bank_account")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RegisteredBankAccountJpaEntity {
    @Id
    @GeneratedValue
    private Long registeredBankAccountId;
    private Long membershipId;
    private String bankName;
    private String bankAccountNumber;
    private boolean linkedStatusIsValid;

    public RegisteredBankAccountJpaEntity(Long membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
    }

    @Override
    public String toString() {
        return "RegisteredBankAccountJpaEntity{" +
                "registeredBankAccountId=" + registeredBankAccountId +
                ", membershipId=" + membershipId +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", linkedStatusIsValid=" + linkedStatusIsValid +
                '}';
    }
}
