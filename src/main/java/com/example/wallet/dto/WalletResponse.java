package com.example.wallet.dto;

import java.util.UUID;

public class WalletResponse {

    private UUID walletId;
    private long balance;

    public WalletResponse(UUID walletId, long balance) {
        this.walletId = walletId;
        this.balance = balance;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public long getBalance() {
        return balance;
    }
}
