package com.example.wallet.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wallet.dto.WalletRequest;
import com.example.wallet.dto.WalletResponse;
import com.example.wallet.model.Wallet;
import com.example.wallet.service.WalletService;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallet")
    public ResponseEntity<WalletResponse> handleWalletOperation(@RequestBody WalletRequest request) {
        Wallet updated = walletService.updateBalance(
                request.getWalletId(),
                request.getOperationType(),
                request.getAmount()
        );
        return ResponseEntity.ok(new WalletResponse(updated.getId(), updated.getBalance()));
    }

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable UUID walletId) {
        Wallet wallet = walletService.getWallet(walletId);
        return ResponseEntity.ok(new WalletResponse(wallet.getId(), wallet.getBalance()));
    }
}
