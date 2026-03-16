
package com.ledger.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransferRequest {

    private String fromId;
    private String toId;
    private BigDecimal amount;

    public String getFromId() {
        return fromId;
    }
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }
    public String getToId() {
        return toId;
    }
    public void setToId(String toId) {
        this.toId = toId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
