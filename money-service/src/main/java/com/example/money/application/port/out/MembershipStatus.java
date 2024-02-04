package com.example.money.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MembershipStatus {
    private final String membershipId;
    private final boolean isValid;

}
