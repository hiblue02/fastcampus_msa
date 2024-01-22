package com.example.banking.application.port.in;

import com.example.banking.domain.RequestFirmbanking;

public interface RequestFirmbankingUseCase {
    RequestFirmbanking requestFirmbanking(RequestFirmbankingCommand command);
}
