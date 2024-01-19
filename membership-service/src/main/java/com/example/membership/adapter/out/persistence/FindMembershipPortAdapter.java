package com.example.membership.adapter.out.persistence;

import com.example.membership.application.port.out.FindMembershipPort;
import com.example.membership.domain.Membership;
import common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FindMembershipPortAdapter implements FindMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;

    @Override
    public MembershipJpaEntity findMembershipByMemberId(Membership.MembershipId membershipId) {
        return membershipRepository.findById(membershipId.getMembershipId()).orElseThrow();
    }
}
