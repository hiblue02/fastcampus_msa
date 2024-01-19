package com.example.membership.adapter.in.web;

import com.example.membership.application.port.in.ModifyMembershipCommand;
import com.example.membership.application.port.in.ModifyMembershipUseCase;
import com.example.membership.domain.Membership;
import common.WebAdapter;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {

    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PostMapping(path = "/membership/modify/{membershipId}")
    ResponseEntity<Membership> modifyMembershipByMemberId(@RequestBody ModifyMembershipRequest request) {
        ModifyMembershipCommand modifyMembershipCommand = ModifyMembershipCommand.builder()
                .membershipId(request.getMembershipId())
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .isValid(request.isValid())
                .isCorp(request.isCorp())
                .build();
        Membership membership = modifyMembershipUseCase.modifyMembership(modifyMembershipCommand);
        return ResponseEntity.ok(membership);
    }
}
