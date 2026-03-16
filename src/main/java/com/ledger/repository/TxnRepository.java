
package com.ledger.repository;

import com.ledger.entity.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn, Long> {
}
