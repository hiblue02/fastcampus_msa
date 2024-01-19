package com.example.membership.application.port.out;

import com.example.membership.adapter.out.persistence.MembershipJpaEntity;
import com.example.membership.domain.Membership;

public interface FindMembershipPort {
    MembershipJpaEntity findMembershipByMemberId(
            Membership.MembershipId membershipId
    );
}
