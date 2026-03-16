
package com.ledger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Txn {

    @Id
    @GeneratedValue
    private Long id;
    private String senderId;
    private String receiverId;
    private BigDecimal amount;
    private Instant timestamp;

    public Txn(){}

    public Txn(String senderId,String receiverId,
               BigDecimal amount,Instant timestamp){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.timestamp = timestamp;
    }
    public Long getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
    // getters setters
}
