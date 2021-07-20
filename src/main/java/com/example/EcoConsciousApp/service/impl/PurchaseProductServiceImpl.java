package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.entity.PurchaseProduct;
import com.example.EcoConsciousApp.entity.PurchaseProductDetail;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.repository.PurchaseProductRepository;
import com.example.EcoConsciousApp.service.PurchaseProductDetailService;
import com.example.EcoConsciousApp.service.PurchaseProductService;
import com.example.EcoConsciousApp.service.UsableProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseProductServiceImpl implements PurchaseProductService {

    @Autowired
    PurchaseProductRepository purchaseProductRepository;

    @Autowired
    PurchaseProductDetailService purchaseProductDetailService;

    @Autowired
    UsableProductService usableProductService;

    @Override
    @Transactional
    public PurchaseProduct transaction(PurchaseProduct purchaseProduct) {
        PurchaseProduct purchaseProductSave = purchaseProductRepository.save(purchaseProduct);
        for (PurchaseProductDetail purchaseProductDetail : purchaseProductSave.getPurchaseProductDetails()) {
            UsableProduct usableProduct = usableProductService.getUsableProductById(purchaseProductDetail.getUsableProduct().getId());
            usableProduct.setUsableProductStock(usableProduct.getUsableProductStock() - purchaseProductDetail.getQuantity());
            usableProductService.saveUsableProduct(usableProduct);

            purchaseProductDetail.setPurchaseProduct(purchaseProductSave);
            purchaseProductDetail.setPriceSell(usableProduct.getUsableProductPrice().doubleValue() * purchaseProductDetail.getQuantity());
            purchaseProductDetailService.savePurchaseProductDetail(purchaseProductDetail);
        }
        return purchaseProductSave;
    }

    @Override
    public PurchaseProduct getTransactionById(String id) {
        return purchaseProductRepository.findById(id).get();
    }
}
