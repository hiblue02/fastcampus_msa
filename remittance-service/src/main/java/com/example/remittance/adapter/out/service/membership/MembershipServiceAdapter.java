package com.example.remittance.adapter.out.service.membership;

import com.example.common.CommonHttpClient;
import com.example.common.ExternalSystemAdapter;
import com.example.remittance.application.port.out.membership.MembershipPort;
import com.example.remittance.application.port.out.membership.MembershipStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


@ExternalSystemAdapter
@RequiredArgsConstructor
public class MembershipServiceAdapter implements MembershipPort {
    private final CommonHttpClient membershipServiceHttpClient;
    @Value("${service.membership.url}")
    private String membershipServiceEndpoint;

    @Override
    public MembershipStatus getMembershipStatus(String membershipId) {
        String buildUrl = String.join("/", this.membershipServiceEndpoint, "membership", membershipId);
        try {
            String jsonResponse = membershipServiceHttpClient.sendGetRequest(buildUrl).body();
            ObjectMapper mapper = new ObjectMapper();

            Membership membership = mapper.readValue(jsonResponse, Membership.class);

            if (membership.isValid()) {
                return new MembershipStatus(membership.getMembershipId(), true);
            } else {
                return new MembershipStatus(membership.getMembershipId(), false);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
