package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.entity.PurchaseProduct;

public interface PurchaseProductService {
    PurchaseProduct transaction(PurchaseProduct purchaseProduct);
    PurchaseProduct getTransactionById(String id);
}
