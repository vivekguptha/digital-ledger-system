Digital Ledger System

This project is a simple digital wallet ledger system developed using Spring Boot and H2 database.
It allows users to create wallets and transfer funds between wallets while handling concurrent transactions safely.

The main goal of this assignment was to design a transfer mechanism that works correctly even when multiple requests try to update the same wallet at the same time.

Assumptions Made

The userId is treated as the unique identifier for a wallet.

The timestamp received in the transfer request is not used. Instead, the system generates the transaction time using Instant.now() inside the service layer to maintain consistency across time zones.

Optimistic locking is used to handle concurrent updates on the wallet balance. This is implemented using the @Version field in the Wallet entity.

A simple retry mechanism (maximum 3 retries) is implemented to handle concurrency conflicts during fund transfer.

The Transaction table is used only as an audit log to store successful transfers. Once created, transaction records are not updated.

No direct entity relationship is maintained between Wallet and Transaction to avoid unnecessary data loading and keep the design simple.

All monetary calculations are done using BigDecimal to avoid precision issues.

H2 in-memory database is used for quick setup and easy testing.


How to Run the Application

Clone the repository.

Run the Spring Boot application using the main class.

Use Postman or any REST client to test the APIs.

Create Wallet

POST /api/wallets

{
  "userId": "user_01",
  "initialBalance": 500
}


Transfer Funds

POST /api/transfer

{
  "fromId": "user_01",
  "toId": "user_02",
  "amount": 100
}


Concurrency Testing

A JUnit test is added to simulate multiple concurrent transfer requests on the same wallet.
This test helps verify that the final wallet balance remains correct even under high contention.

Design Highlights

Transactional fund transfer to ensure atomicity.

Optimistic locking to prevent lost updates.

Retry handling for concurrency conflicts.

Asynchronous audit logging to avoid blocking the main transfer flow.