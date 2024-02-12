package com.example.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataRegisteredBankAccountRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
    @Query("SELECT e  FROM RegisteredBankAccountJpaEntity e WHERE e.membershipId = :membershipId limit 1")
    RegisteredBankAccountJpaEntity findByMembershipId(@Param("membershipId") String membershipId);
}
