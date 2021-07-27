package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.entity.VendorProductScrapsPurchase;
import com.example.EcoConsciousApp.entity.VendorUsableProductRegistration;

public interface VendorUsableProductRegistrationService {
    VendorUsableProductRegistration transaction(VendorUsableProductRegistration registration);
    VendorUsableProductRegistration getTransactionByiD(String id);
}
