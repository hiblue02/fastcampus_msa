package com.example.money.adapter.out.persistence;

import com.example.common.PersistenceAdapter;
import com.example.money.application.port.out.IncreaseMoneyPort;
import com.example.money.domain.MemberMoney;
import com.example.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.List;
import java.sql.Timestamp;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort {

    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;

    private final SpringDataMemberMoneyRepository memberMoneyRepository;
    @Override
    public MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.ChangingTypeCode changingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.ChangingMoneyStatusCode changingMoneyStatusCode,
            MoneyChangingRequest.Uuid uuid) {
        return moneyChangingRequestRepository.save(
                new MoneyChangingRequestJpaEntity(
                        targetMembershipId.getTargetMembershipId(),
                        changingType.getChangingType().getValue(),
                        changingMoneyAmount.getChangingMoneyAmount(),
                        new Timestamp(System.currentTimeMillis()), // TODO: 2021-08-17 11:00:00
                        changingMoneyStatusCode.getChangingMoneyStatus().getValue(),
                        UUID.randomUUID()
                )
        );
    }

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId memberId, int increaseMoneyAmount) {
        MemberMoneyJpaEntity entity;
        try {
            List<MemberMoneyJpaEntity> entityList =  memberMoneyRepository.findByMembershipId(Long.parseLong(memberId.getMembershipId()));
            entity = entityList.get(0);

            entity.setBalance(entity.getBalance() + increaseMoneyAmount);
            return  memberMoneyRepository.save(entity);
        } catch (Exception e){
            entity = new MemberMoneyJpaEntity(
                    Long.parseLong(memberId.getMembershipId()),
                    increaseMoneyAmount
            );
            entity = memberMoneyRepository.save(entity);
            return entity;
        }

//
//        entity.setBalance(entity.getBalance() + increaseMoneyAmount);
//        return  memberMoneyRepository.save(entity);
    }
}
