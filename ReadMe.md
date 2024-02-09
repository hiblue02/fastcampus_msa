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
