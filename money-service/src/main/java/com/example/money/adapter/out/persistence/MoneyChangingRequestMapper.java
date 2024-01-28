package com.example.money.adapter.out.persistence;

import com.example.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingRequestMapper {

    public MoneyChangingRequest mapToDomainEntity(MoneyChangingRequestJpaEntity jpaEntity) {
        return MoneyChangingRequest.generateMoneyChangingRequest(
                new MoneyChangingRequest.MoneyChangingRequestId(jpaEntity.getTargetMembershipId()),
                new MoneyChangingRequest.TargetMembershipId(jpaEntity.getTargetMembershipId()),
                new MoneyChangingRequest.ChangingTypeCode(MoneyChangingRequest.ChangingType.getCode(jpaEntity.getMoneyChangingType())),
                new MoneyChangingRequest.ChangingMoneyAmount(jpaEntity.getMoneyAmount()),
                new MoneyChangingRequest.ChangingMoneyStatusCode(MoneyChangingRequest.ChangingMoneyStatus.getCode(jpaEntity.getChangingMoneyStatus())),
                new MoneyChangingRequest.Uuid(jpaEntity.getUuid()),
                new MoneyChangingRequest.CreatedAt(jpaEntity.getTimestamp())
        );
    }

}
