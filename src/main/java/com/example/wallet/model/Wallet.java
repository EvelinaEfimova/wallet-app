package com.example.wallet.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    private UUID id;

    @Column(nullable = false)
    private long balance;

    public Wallet() {}

    public Wallet(UUID id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
