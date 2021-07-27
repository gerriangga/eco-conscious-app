package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.entity.VendorProductScrapsPurchaseDetail;
import com.example.EcoConsciousApp.repository.VendorProductScrapsPurchaseDetailRepository;
import com.example.EcoConsciousApp.service.VendorProductScrapsPurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorProductScrapsPurchaseDetaillmpl implements VendorProductScrapsPurchaseDetailService {
    @Autowired
    VendorProductScrapsPurchaseDetailRepository purchaseDetailRepository;
    @Override
    public VendorProductScrapsPurchaseDetail savePurchaseDetail(VendorProductScrapsPurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }
}
