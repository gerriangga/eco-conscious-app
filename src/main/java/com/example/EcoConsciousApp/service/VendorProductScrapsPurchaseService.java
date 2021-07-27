package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.entity.VendorProductScrapsPurchase;

public interface VendorProductScrapsPurchaseService {
    VendorProductScrapsPurchase transaction(VendorProductScrapsPurchase purchase);
    VendorProductScrapsPurchase getTransactionByiD(String id);
}
