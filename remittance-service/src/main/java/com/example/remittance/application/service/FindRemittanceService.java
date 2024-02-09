package com.example.remittance.application.service;

import com.example.common.UseCase;
import com.example.remittance.application.port.in.FindRemittanceCommand;
import com.example.remittance.application.port.in.FindRemittanceUseCase;
import com.example.remittance.domain.RemittanceRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindRemittanceService implements FindRemittanceUseCase {
    @Override
    public List<RemittanceRequest> findRemiitanceHistory(FindRemittanceCommand command) {
        return null;
    }
}
