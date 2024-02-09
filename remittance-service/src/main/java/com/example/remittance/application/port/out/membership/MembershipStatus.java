package com.example.remittance.application.port.out.membership;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MembershipStatus {
    private String membershipId;
    private boolean isValid;
}
