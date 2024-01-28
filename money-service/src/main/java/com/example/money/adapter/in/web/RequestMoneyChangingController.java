package com.example.money.adapter.in.web;

import com.example.common.WebAdapter;
import com.example.money.application.port.in.IncreaseMoneyRequestCommand;
import com.example.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.example.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestMoneyChangingController {

    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;

    @GetMapping(path = "/money/increase}")
    ResponseEntity<MoneyChangingResultDetail> findMembershipByMemberId(@RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest response = increaseMoneyRequestUseCase.increaseMoneyRequest(command);

        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                response.getMoneyChangingRequestId(),
                response.getChangingMoneyAmount(),
                MoneyChangingResultDetail.MoneyChangingType.of(response.getChangingType()),
                MoneyChangingResultDetail.MoneyChangingResultStatus.of(response.getChangingMoneyStatus())
        );

        return ResponseEntity.ok(resultDetail);
    }
}
