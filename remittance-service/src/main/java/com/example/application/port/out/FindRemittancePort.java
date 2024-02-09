package com.example.application.port.out;

import com.example.application.port.in.FindRemittanceCommand;

public interface FindRemittancePort {
    List<RemittanceRequestJpaEntity> findRemittanceHistory(FindRemittanceCommand command);
}
