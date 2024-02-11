package com.example.money.application.port.in;

import com.example.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.example.money.domain.MemberMoney;

public interface GetMemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(
            MemberMoney.MembershipId memberId
    );
}
