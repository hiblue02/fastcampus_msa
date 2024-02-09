package com.example.remittance.application.port.out;

import com.example.remittance.application.port.in.FindRemittanceCommand;

public interface FindRemittancePort {
    List<RemittanceRequestJpaEntity> findRemittanceHistory(FindRemittanceCommand command);
}
