package com.example.money.adapter.out.persistence;

import com.example.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisterMembershipPortAdapter implements RegisterMembershipPort {

    private final SpringDataMoneyChangingRequestRepository membershipRepository;

    @Override
    public MemberMoneyJpaEntity createMembership(Membership.MembershipName membershipName,
                                                 Membership.MembershipEmail membershipEmail,
                                                 Membership.MembershipAddress membershipAddress,
                                                 Membership.MembershipIsValid membershipIsValid,
                                                 Membership.MembershipIsCorp membershipIsCorp) {

        return membershipRepository.save(
                new MemberMoneyJpaEntity(
                        membershipName.getNameValue(),
                        membershipAddress.getAddressValue(),
                        membershipEmail.getEmailValue(),
                        membershipIsValid.isValidValue(),
                        membershipIsCorp.isCorpValue()
                )
        );
    }
}
