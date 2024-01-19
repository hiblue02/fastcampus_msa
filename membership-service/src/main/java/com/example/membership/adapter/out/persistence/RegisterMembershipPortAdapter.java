package com.example.membership.adapter.out.persistence;

import com.example.membership.application.port.out.RegisterMembershipPort;
import com.example.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import com.example.common.PersistenceAdapter;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisterMembershipPortAdapter implements RegisterMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;

    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName,
                                                Membership.MembershipEmail membershipEmail,
                                                Membership.MembershipAddress membershipAddress,
                                                Membership.MembershipIsValid membershipIsValid,
                                                Membership.MembershipIsCorp membershipIsCorp) {

        return membershipRepository.save(
                new MembershipJpaEntity(
                        membershipName.getNameValue(),
                        membershipAddress.getAddressValue(),
                        membershipEmail.getEmailValue(),
                        membershipIsValid.isValidValue(),
                        membershipIsCorp.isCorpValue()
                )
        );
    }
}
