package com.example.application.port.out;

public interface RequestRemittancePort {

    RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command);

    boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity);
}
