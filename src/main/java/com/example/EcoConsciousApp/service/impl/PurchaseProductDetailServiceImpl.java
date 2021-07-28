package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.entity.PurchaseProductDetail;
import com.example.EcoConsciousApp.repository.PurchaseProductDetailRepository;
import com.example.EcoConsciousApp.service.PurchaseProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseProductDetailServiceImpl implements PurchaseProductDetailService {

    @Autowired
    PurchaseProductDetailRepository purchaseProductDetailRepository;

    @Override
    public PurchaseProductDetail savePurchaseProductDetail(PurchaseProductDetail purchaseProductDetail) {
        return purchaseProductDetailRepository.save(purchaseProductDetail);
    }
}
