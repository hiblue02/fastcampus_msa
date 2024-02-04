package com.example.money.adapter.out.service;

import com.example.common.CommonHttpClient;
import com.example.membership.domain.Membership;
import com.example.money.application.port.out.GetMembershipPort;
import com.example.money.application.port.out.MembershipStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MembershipServiceAdapter implements GetMembershipPort {

    private final CommonHttpClient commonHttpClient;

    private final String membershipServiceUrl;

    public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
                                    @Value("${service.membership.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.membershipServiceUrl = membershipServiceUrl;
    }


    @Override
    public MembershipStatus getMembership(String membershipId) {
        String url = String.join("/", membershipServiceUrl, "membership", membershipId);
        try {
            // 맴버십 조회
            String response = commonHttpClient.sendGetRequest(url).body();
            ObjectMapper mapper = new ObjectMapper();
            Membership membership = mapper.readValue(response, Membership.class);

            // 맴버십 상태 반환
            if (membership.isValid()) {
                return new MembershipStatus(membership.getMembershipId()+"", true);
            } else {
                return new MembershipStatus(membership.getMembershipId()+"", false);
            }

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
