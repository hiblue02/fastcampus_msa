package com.example.membership.adapter.in.web;

import com.example.membership.application.port.in.FindMembershipQuery;
import com.example.membership.application.port.in.FindMembershipUseCase;
import com.example.membership.domain.Membership;
import common.WebAdapter;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {

    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping(path = "/membership/{membershipId}")
    ResponseEntity<Membership> findMembershipByMemberId(@PathVariable(name = "membershipId") Long membershipId) {
        FindMembershipQuery membershipQuery = FindMembershipQuery.builder()
                .membershipId(membershipId)
                .build();

        Membership membership = findMembershipUseCase.findMembershipByMemberId(membershipQuery);

        return ResponseEntity.ok(membership);
    }


}
