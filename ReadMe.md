## 실습 4 - kafka로 IPC 패턴 구현하기
#### 프로세스 흐름
1. Money-Service > IncreateMoneyRequestService : RechargingMoneyTask Produce
   - 멤버십 유효성 검사, 뱅킹 계좌 유효성 검사 subtask를 RechargingMoneyTask로 produce
2. task-consumer > TaskConsumer : RechargingMoneyTask consume 
    - RechargingMoneyTask consume
    - subtask 실행
    - 실행결과 subTask에 넣어 반환
3. task-consumer > TaskResultProducer : task result (RechargingMoneyTask) Produce
4. money-service > RechargingMoneyResultConsumer : task result (RechargingMoneyTask) Consume
5.  Money-Service > IncreateMoneyRequestService : money.increaseMoney 실행

## [트랜잭션](src/main/resources/docs/트랜잭션.md)
## 결제 비즈니스 정의
1. 고객 유효성을 확인한다. (validate Memebership)
2. 머니 잔액을 확인하고, 부족하면 충전한다. (validate Money, request MoneyRecharging)
3. 가맹점 유효성을 확인한다. (validate franchise)
4. 머니에서 가맹점 대금을 출금한다(승인). (decrease Money)
5. 결제 결과를 저장한다. (Payment Result Insert)
