package com.example.money.application.port.out;

import com.example.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.example.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.example.money.domain.MemberMoney;
import com.example.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyPort {

    MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.ChangingTypeCode changingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.ChangingMoneyStatusCode moneyChangingStatus,
            MoneyChangingRequest.Uuid uuid);

    MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId memberId,
            int increaseMoneyAmount);

}

