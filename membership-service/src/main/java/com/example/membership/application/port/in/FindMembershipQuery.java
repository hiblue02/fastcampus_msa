package com.example.membership.application.port.in;

import com.example.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindMembershipQuery extends SelfValidating<FindMembershipQuery> {

    @NotNull
    private final Long membershipId;

    public FindMembershipQuery(Long membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
