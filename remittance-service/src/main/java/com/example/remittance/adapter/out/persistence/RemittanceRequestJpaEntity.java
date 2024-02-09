package com.example.remittance.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request_remittance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemittanceRequestJpaEntity {
    @Id
    @GeneratedValue
    private Long remittanceRequestId;
    private String fromMembershipId;
    private String toMembershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int remittanceType;
    private int amount;
    private String remittanceStatus;
}
