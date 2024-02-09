package com.example.remittance.application.port.in;

import com.example.remittance.domain.RemittanceRequest;

import java.util.List;

public interface FindRemittanceUseCase {
    List<RemittanceRequest> findRemiitanceHistory(FindRemittanceCommand command);
}
