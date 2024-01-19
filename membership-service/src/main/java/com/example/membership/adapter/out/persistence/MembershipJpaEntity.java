package com.example.membership.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membership")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long membershipId;

    private String name;

    private String address;

    private String email;

    private boolean isValid;
    private boolean isCorp;

    public MembershipJpaEntity(String name, String address, String email, boolean isValid, boolean isCorp) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.isValid = isValid;
        this.isCorp = isCorp;
    }
}
