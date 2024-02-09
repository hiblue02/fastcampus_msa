package com.example.remittance.application.port.out;

import com.example.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.example.remittance.application.port.in.FindRemittanceCommand;

import java.util.List;

public interface FindRemittancePort {
    List<RemittanceRequestJpaEntity> findRemittanceHistory(FindRemittanceCommand command);
}
