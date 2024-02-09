package com.example.remittance.application.port.out;

import com.example.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.example.remittance.application.port.in.RequestRemittanceCommand;

public interface RequestRemittancePort {

    RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command);

    boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity);
}
