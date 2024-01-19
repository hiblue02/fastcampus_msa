package com.example.membership.application.service;

import com.example.membership.adapter.out.persistence.MembershipJpaEntity;
import com.example.membership.adapter.out.persistence.MembershipMapper;
import com.example.membership.application.port.in.FindMembershipQuery;
import com.example.membership.application.port.in.FindMembershipUseCase;
import com.example.membership.application.port.out.FindMembershipPort;
import com.example.membership.domain.Membership;
import common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort membershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembershipByMemberId(FindMembershipQuery membershipQuery) {
        MembershipJpaEntity membershipJpaEntity = membershipPort.findMembershipByMemberId(
                new Membership.MembershipId(membershipQuery.getMembershipId())
        );

        return membershipMapper.mapToDomainEntity(membershipJpaEntity);
    }
}
