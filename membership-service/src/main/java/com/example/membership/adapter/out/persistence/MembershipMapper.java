package com.example.membership.adapter.out.persistence;

import com.example.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {

    public Membership mapToDomainEntity(MembershipJpaEntity jpaEntity) {
        return Membership.generateMember(
                new Membership.MembershipId(jpaEntity.getMembershipId()),
                new Membership.MembershipName(jpaEntity.getName()),
                new Membership.MembershipEmail(jpaEntity.getEmail()),
                new Membership.MembershipAddress(jpaEntity.getAddress()),
                new Membership.MembershipIsValid(jpaEntity.isValid()),
                new Membership.MembershipIsCorp(jpaEntity.isCorp())
        );
    }

}
