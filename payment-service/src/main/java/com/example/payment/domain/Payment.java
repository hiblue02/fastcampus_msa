package com.example.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Payment {
    private Long paymentId;
    private String requestMembershipId;
    private int requestPrice;
    private String franchiseId;
    private String franchiseFeeRate;
    private int paymentStatus;
    private Date approvedAt;

    public static Payment generatePayment(
            PaymentId paymentId,
            RequestMembershipId requestMembershipId,
            RequestPrice requestPrice,
            FranchiseId franchiseId,
            FranchiseFeeRate franchiseFeeRate,
            PaymentStatus paymentStatus,
            ApprovedAt approvedAt) {
        return new Payment(paymentId.paymentId(),
                requestMembershipId.requestMembershipId(),
                requestPrice.requestPrice(),
                franchiseId.franchiseId(),
                franchiseFeeRate.franchiseFeeRate(),
                paymentStatus.paymentStatus(),
                approvedAt.approvedAt()
        );
    }

    public record PaymentId(Long paymentId) {
    }

    public record RequestMembershipId(String requestMembershipId) {
    }

    public record RequestPrice(int requestPrice) {
    }

    public record FranchiseId(String franchiseId) {
    }

    public record FranchiseFeeRate(String franchiseFeeRate) {
    }

    public record PaymentStatus(int paymentStatus) {
    }

    public record ApprovedAt(Date approvedAt) {
    }
}
