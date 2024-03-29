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

## 데이터 쿼리
### Aggregation Pattern
1. 너무 자주 호출되면 각 api 서비스에 부하를 줄 수 있다. 
2. DB 부하 또는 다른 서비스로 장애를 전파시킬 수 있다.
3. 여러 번의 api 호출 중 한 번이라도 실패하면, 전체가 실패여야 한다. 
4. 여러 번 api 호출로 인해 Latency가 길다. 
### CQRS Pattern
1. 데이터 변동이 생기면 다른 서비스로 알려준다. (비동기, 이벤트 발행)
2. 다만, 데이터를 변동 시키는 주체는 한 곳이어야 한다. 다른 곳에서는 가지고 있지만, 직접 데이터를 변동시키진 않는다. 
3. 데이터의 변동(Command)와 데이터 접근(Query)를 분리하는 패턴이다.
4. 
