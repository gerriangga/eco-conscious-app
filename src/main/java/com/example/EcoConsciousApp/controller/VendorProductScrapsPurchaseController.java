package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.VendorProductScrapsPurchase;
import com.example.EcoConsciousApp.service.VendorProductScrapsPurchaseService;
import com.example.EcoConsciousApp.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VendorProductScrapsPurchaseController {
    @Autowired
    VendorProductScrapsPurchaseService purchaseService;

    @PostMapping("/purchase_transactions")
    public ResponseEntity<Response<VendorProductScrapsPurchase>> purchase
            (@RequestBody VendorProductScrapsPurchase purchase){
        String message = String.format(ResponseMessage.DATA_INSERTED, "purchase");
        Response<VendorProductScrapsPurchase> response = new Response<>();
        response.setMessage(message);
        response.setData(purchaseService.transaction(purchase));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }


    @GetMapping("/purchase_transactions/{purchaseId}")
    public VendorProductScrapsPurchase getPurchaseById(@PathVariable String purchaseId){
        return purchaseService.getTransactionByiD(purchaseId);
    }

}
