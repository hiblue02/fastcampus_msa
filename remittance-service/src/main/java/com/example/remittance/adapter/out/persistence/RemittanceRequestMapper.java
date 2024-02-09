package com.example.remittance.adapter.out.persistence;

import com.example.remittance.domain.RemittanceRequest;
import org.springframework.stereotype.Component;

@Component
public class RemittanceRequestMapper {
    public RemittanceRequest mapToDomainEntity(RemittanceRequestJpaEntity jpaEntity) {
        return RemittanceRequest.generateRemittanceRequest(
                new RemittanceRequest.RemittanceRequestId(jpaEntity.getRemittanceRequestId()+""),
                new RemittanceRequest.RemittanceFromMembershipId(jpaEntity.getFromMembershipId()),
                new RemittanceRequest.ToBankName(jpaEntity.getToBankName()),
                new RemittanceRequest.ToBankAccountNumber(jpaEntity.getToBankAccountNumber()),
                new RemittanceRequest.RemittanceType(jpaEntity.getRemittanceType()),
                new RemittanceRequest.Amount(jpaEntity.getAmount()),
                new RemittanceRequest.RemittanceStatus(jpaEntity.getRemittanceStatus())
                );
    }

}
