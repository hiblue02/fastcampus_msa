package com.example.banking.application.port.out;

import com.example.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.example.banking.adapter.out.external.bank.FirmbankingResult;

public interface RequestExternalFirmbanking {
   FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}
