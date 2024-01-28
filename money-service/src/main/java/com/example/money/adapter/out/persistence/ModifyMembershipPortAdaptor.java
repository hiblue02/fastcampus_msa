package com.example.money.adapter.out.persistence;

import com.example.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ModifyMembershipPortAdaptor implements ModifyMembershipPort {

    private final SpringDataMoneyChangingRequestRepository membershipRepository;

    @Override
    public MemberMoneyJpaEntity modifyMembership(Membership.MembershipId membershipId,
                                                 Membership.MembershipName membershipName,
                                                 Membership.MembershipEmail membershipEmail,
                                                 Membership.MembershipAddress membershipAddress,
                                                 Membership.MembershipIsValid membershipIsValid,
                                                 Membership.MembershipIsCorp membershipIsCorp) {

        MemberMoneyJpaEntity membershipJpaEntity
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
