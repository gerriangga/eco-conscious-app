package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.dto.WalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class WalletRestTemplateController {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/wallets")
    public ResponseEntity<WalletDTO> createWallet(@RequestBody WalletDTO wallet) {
        String url = ApiUrlConstant.WALLET_REST_TEMPLATE;
        WalletDTO walletDTO = new WalletDTO(wallet.getPhoneNumber(), wallet.getBalance());

        ResponseEntity<WalletDTO> responseEntity = restTemplate.postForEntity(URI.create(url),
                walletDTO,
                WalletDTO.class);
        return responseEntity;
    }

}
