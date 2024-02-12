package com.example.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpringDataRequestFirmbankingRepository extends JpaRepository<RequestFirmbankingJpaEntity, Long> {
    @Query("SELECT e  FROM RequestFirmbankingJpaEntity e WHERE e.aggregateIdentifier = :aggregateIdentifier limit 1")
    RequestFirmbankingJpaEntity findByAggregateIdentifier(String aggregateIdentifier);
}
