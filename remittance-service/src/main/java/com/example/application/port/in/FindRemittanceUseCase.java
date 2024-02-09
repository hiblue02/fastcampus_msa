package com.example.application.port.in;

import com.example.remittance.RemittanceRequest;

import java.util.List;

public interface FindRemittanceUseCase {
    List<RemittanceRequest> findRemiitanceHistory(FindRemittanceCommand command);
}
