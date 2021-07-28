package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.dto.TransactionSearchDTO;
import com.example.EcoConsciousApp.entity.PurchaseProduct;

import java.util.List;

public interface PurchaseProductService {
    PurchaseProduct transaction(PurchaseProduct purchaseProduct);

    PurchaseProduct getTransactionById(String id);

    List<PurchaseProduct> getAllTransaction(TransactionSearchDTO transactionSearchDTO);

}
