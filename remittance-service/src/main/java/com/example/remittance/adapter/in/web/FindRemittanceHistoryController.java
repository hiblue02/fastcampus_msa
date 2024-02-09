package com.example.remittance.adapter.in.web;

import com.example.common.WebAdapter;
import com.example.remittance.application.port.in.FindRemittanceCommand;
import com.example.remittance.application.port.in.FindRemittanceUseCase;
import com.example.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindRemittanceHistoryController {
    private final FindRemittanceUseCase findRemittanceUseCase;

    @GetMapping( "/remittance/{membershipId}")
    public List<RemittanceRequest> findRemittanceHistory(@PathVariable String membershipId) {
        FindRemittanceCommand command = FindRemittanceCommand.builder()
                .membershipId(membershipId)
                .build();
        return findRemittanceUseCase.findRemiitanceHistory(command);
    }
}
