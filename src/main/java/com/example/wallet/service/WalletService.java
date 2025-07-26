package com.example.wallet.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet.model.Wallet;
import com.example.wallet.repository.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Wallet getWallet(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found: " + walletId));
    }

    @Transactional
    public Wallet updateBalance(UUID walletId, String operationType, long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Wallet wallet = walletRepository.findByIdForUpdate(walletId)
            .orElseGet(() -> new Wallet(walletId, 0L));

        switch (operationType.toUpperCase()) {
            case "DEPOSIT":
                wallet.setBalance(wallet.getBalance() + amount);
                break;

            case "WITHDRAW":
                if (wallet.getBalance() < amount) {
                    throw new IllegalArgumentException("Insufficient funds");
                }
                wallet.setBalance(wallet.getBalance() - amount);
                break;

            default:
                throw new IllegalArgumentException("Invalid operation type: " + operationType);
        }

        return walletRepository.save(wallet);
    }
}
