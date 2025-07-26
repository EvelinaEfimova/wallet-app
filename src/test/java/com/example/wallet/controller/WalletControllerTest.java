package com.example.wallet.controller;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.wallet.model.Wallet;
import com.example.wallet.service.WalletService;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    @Test
    public void testDepositAndBalance() throws Exception {
        UUID walletId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Wallet mockWallet = new Wallet(walletId, 1000);

        when(walletService.updateBalance(walletId, "DEPOSIT", 1000)).thenReturn(mockWallet);

        mockMvc.perform(post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"walletId\":\"123e4567-e89b-12d3-a456-426614174000\", \"operationType\":\"DEPOSIT\", \"amount\":1000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.walletId").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$.balance").value(1000));
    }
}
