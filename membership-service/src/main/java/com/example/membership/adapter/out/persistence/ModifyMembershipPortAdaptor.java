package com.example.membership.adapter.out.persistence;

import com.example.membership.application.port.out.ModifyMembershipPort;
import com.example.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import com.example.common.PersistenceAdapter;

@PersistenceAdapter
@RequiredArgsConstructor
public class ModifyMembershipPortAdaptor implements ModifyMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;

    @Override
    public MembershipJpaEntity modifyMembership(Membership.MembershipId membershipId,
                                                Membership.MembershipName membershipName,
                                                Membership.MembershipEmail membershipEmail,
                                                Membership.MembershipAddress membershipAddress,
                                                Membership.MembershipIsValid membershipIsValid,
                                                Membership.MembershipIsCorp membershipIsCorp) {

        MembershipJpaEntity membershipJpaEntity
                = membershipRepository
                .findById(membershipId.getMembershipId())
                .orElseThrow();

        membershipJpaEntity.setName(membershipJpaEntity.getName());
        membershipJpaEntity.setEmail(membershipEmail.getEmailValue());
        membershipJpaEntity.setAddress(membershipAddress.getAddressValue());
        membershipJpaEntity.setValid(membershipIsValid.isValidValue());
        membershipJpaEntity.setCorp(membershipIsCorp.isCorpValue());

        return membershipRepository.save(membershipJpaEntity);

    }
}
