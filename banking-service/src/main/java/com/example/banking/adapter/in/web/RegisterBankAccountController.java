package com.example.banking.adapter.in.web;

import com.example.banking.application.port.in.RegisterBankAccountCommand;
import com.example.banking.application.port.in.RegisterBankAccountUseCase;
import com.example.banking.domain.RegisteredBankAccount;
import com.example.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping(path = "/bankaccount/register")
    ResponseEntity<RegisteredBankAccount> registerMembership(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankAccountNumber(request.getBankAccount())
                .bankName(request.getBankName())
                .linkedStatusIsValid(request.isLinkedStatusIsValid())
                .build();

        return ResponseEntity.ok(registerBankAccountUseCase.registerBankAccount(command));
    }
}
