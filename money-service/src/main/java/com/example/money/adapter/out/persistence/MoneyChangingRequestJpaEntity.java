package com.example.money.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "money_changing_request")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoneyChangingRequestJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long moneyChangingRequestId;

    private String targetMembershipId;

    private int moneyChangingType; // 0: 증액, 1: 감액

    private int moneyAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private int changingMoneyStatus; // 0: 요청, 1: 성공, 2: 실패

    private String uuid;

    public MoneyChangingRequestJpaEntity(String targetMembershipId, int moneyChangingType, int moneyAmount, Date timestamp, int changingMoneyStatus, UUID uuid) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingType = moneyChangingType;
        this.moneyAmount = moneyAmount;
        this.timestamp = timestamp;
        this.changingMoneyStatus = changingMoneyStatus;
        this.uuid = uuid.toString();
    }
}
