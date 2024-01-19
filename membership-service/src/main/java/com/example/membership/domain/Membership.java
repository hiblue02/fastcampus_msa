package com.example.membership.domain;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Data
public class Membership {
    private final Long membershipId;
    private final String name;
    private final String email;
    private final String address;
    private final boolean isValid;
    private final boolean isCorp;


    public static Membership generateMember(
            MembershipId membershipId,
            MembershipName membershipName,
            MembershipEmail membershipEmail,
            MembershipAddress membershipAddress,
            MembershipIsValid isValid,
            MembershipIsCorp isCorp

    ) {
        return new Membership(
                membershipId.membershipId,
                membershipName.nameValue,
                membershipEmail.emailValue,
                membershipAddress.addressValue,
                isValid.isValidValue,
                isCorp.isCorpValue
        );
    }

    @Value
    public static class MembershipId {
        public MembershipId(Long value) {
            this.membershipId = value;
        }

        Long membershipId;
    }

    @Value
    public static class MembershipName {
        public MembershipName(String value) {
            this.nameValue = value;
        }

        String nameValue;
    }

    @Value
    public static class MembershipEmail {
        public MembershipEmail(String value) {
            this.emailValue = value;
        }

        String emailValue;
    }

    @Value
    public static class MembershipAddress {
        public MembershipAddress(String value) {
            this.addressValue = value;
        }

        String addressValue;
    }

    @Value
    public static class MembershipIsValid {
        public MembershipIsValid(boolean value) {
            this.isValidValue = value;
        }

        boolean isValidValue;
    }

    @Value
    public static class MembershipIsCorp {
        public MembershipIsCorp(boolean value) {
            this.isCorpValue = value;
        }

        boolean isCorpValue;
    }
}
