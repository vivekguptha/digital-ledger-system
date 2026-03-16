
package com.ledger.service;

import com.ledger.entity.Txn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    @Async
    public void log(Txn txn){
        System.out.println("AUDIT LOG TXN: " + txn.getId());
        try{ Thread.sleep(200);}catch(Exception e){}
    }
}
