package com.example.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpringDataRequestFirmbankingRepository extends JpaRepository<RequestFirmbankingJpaEntity, Long> {
    @Query("SELECT e  FROM FirmbankingRequestJpaEntity e WHERE e.aggregateIdentifier = :aggregateIdentifier OFFSET 1")
    RequestFirmbankingJpaEntity findByAggregateIdentifier(String aggregateIdentifier);
}
