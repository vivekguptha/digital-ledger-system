
package com.ledger;

import com.ledger.entity.Wallet;
import com.ledger.repository.WalletRepository;
import com.ledger.service.TransferService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.*;

@SpringBootTest
class ConcurrencyTest {

    @Autowired
    TransferService service;

    @Autowired
    WalletRepository repo;

    @BeforeEach
    void setup(){

        repo.deleteAll();

        repo.save(new Wallet("A", new BigDecimal("10000"), 0L));
        repo.save(new Wallet("B", new BigDecimal("0"), 0L));
    }

    @Test
    void test100ConcurrentTransfers() throws Exception {

        ExecutorService ex = Executors.newFixedThreadPool(20);

        for(int i=0;i<100;i++){
            ex.submit(() -> service.transfer("A","B", new BigDecimal("10")));
        }

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);

        Wallet a = repo.findById("A").get();
        Wallet b = repo.findById("B").get();

        System.out.println("A balance = " + a.getBalance());
        System.out.println("B balance = " + b.getBalance());
    }
}
