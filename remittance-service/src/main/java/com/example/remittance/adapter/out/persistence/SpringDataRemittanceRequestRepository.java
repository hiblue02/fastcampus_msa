package com.example.remittance.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRemittanceRequestRepository extends JpaRepository<RemittanceRequestJpaEntity, Long> {

}
