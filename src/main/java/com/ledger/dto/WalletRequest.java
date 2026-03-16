
package com.ledger.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WalletRequest {

    private String userId;
    private BigDecimal initialBalance;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
}