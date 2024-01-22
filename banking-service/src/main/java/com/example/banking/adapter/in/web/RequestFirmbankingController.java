package com.example.banking.adapter.in.web;

import com.example.banking.application.port.in.RequestFirmbankingCommand;
import com.example.banking.application.port.in.RequestFirmbankingUseCase;
import com.example.banking.domain.RequestFirmbanking;
import com.example.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmbankingController {

    private final RequestFirmbankingUseCase requestFirmbankingUseCase;

    @PostMapping(path = "/banking/firmbanking/request")
    ResponseEntity<RequestFirmbanking> requestFirmbanking(@RequestBody RequestFirmbankingRequest request) {
        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();

        RequestFirmbanking firmbankingRequest = requestFirmbankingUseCase.requestFirmbanking(command);

        return ResponseEntity.ok(firmbankingRequest);
    }
}
