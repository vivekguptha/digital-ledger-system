
package com.ledger.service;

import com.ledger.entity.*;
import com.ledger.exception.*;
import com.ledger.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor

public class TransferService {

    private final WalletRepository walletRepo;
    private final TxnRepository txnRepo;
    private final AuditService auditService;

    public TransferService(WalletRepository walletRepo,
                           TxnRepository txnRepo,
                           AuditService auditService) {
        this.walletRepo = walletRepo;
        this.txnRepo = txnRepo;
        this.auditService = auditService;
    
    }
    private static final int MAX_RETRY = 3;

    @Transactional
    public void transfer(String fromId, String toId, BigDecimal amount){

        int attempt = 0;

        while(attempt < MAX_RETRY){

            try{

                Wallet sender = walletRepo.findById(fromId).orElseThrow();
                Wallet receiver = walletRepo.findById(toId).orElseThrow();

                if(sender.getBalance().compareTo(amount) < 0)
                    throw new InsufficientFundsException();

                sender.setBalance(sender.getBalance().subtract(amount));
                receiver.setBalance(receiver.getBalance().add(amount));

                walletRepo.save(sender);
                walletRepo.save(receiver);

                Txn txn = new Txn(fromId, toId, amount, Instant.now());

                txnRepo.save(txn);

                auditService.log(txn);

                return;

            }catch(ObjectOptimisticLockingFailureException e){
                attempt++;
                if(attempt >= MAX_RETRY)
                    throw new ConcurrencyException();
            }
        }
    }
}
