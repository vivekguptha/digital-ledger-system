
package com.ledger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {
    @Id
    private String userId;
    private BigDecimal balance;
    @Version
    private Long version;  
    public Wallet() {
    }
    public Wallet(String userId, BigDecimal balance, Long version) {
        this.userId = userId;
        this.balance = balance;
        this.version = version;
    }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}
