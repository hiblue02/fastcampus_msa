package com.example.remittance.application.service;

import com.example.common.UseCase;
import com.example.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.example.remittance.application.port.in.RequestRemittanceCommand;
import com.example.remittance.application.port.out.RequestRemittancePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestRemittanceService implements RequestRemittancePort {
    @Override
    public RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command) {
        return null;
    }

    @Override
    public boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity) {
        return false;
    }
}
