package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.ProductScraps;
import com.example.EcoConsciousApp.entity.VendorProductScrapsPurchase;
import com.example.EcoConsciousApp.entity.VendorProductScrapsPurchaseDetail;
import com.example.EcoConsciousApp.exception.DataNotFoundException;
import com.example.EcoConsciousApp.repository.VendorProductScrapsPurchaseRepository;
import com.example.EcoConsciousApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VendorProductScrapsPurchaselmpl implements VendorProductScrapsPurchaseService {
    @Autowired
    VendorProductScrapsPurchaseRepository purchaseRepository;

    @Autowired
    VendorProductScrapsPurchaseDetailService purchaseDetailService;

    @Autowired
    ProductScrapsService productService;

    @Autowired
    VendorService vendorService;

    @Override
    @Transactional
    public VendorProductScrapsPurchase transaction(VendorProductScrapsPurchase purchase) {
        VendorProductScrapsPurchase purchase1 = purchaseRepository.save(purchase);
        for (VendorProductScrapsPurchaseDetail purchaseDetail : purchase1.getPurchaseDetails()) {
            ProductScraps product = productService.getProductScrapsById(purchaseDetail.getProductScraps().getId());
            product.setStock(product.getStock() - purchaseDetail.getQuantity());
            productService.saveProductScraps(product);
            purchaseDetail.setPurchase(purchase1);
            purchaseDetail.setSubtotal(product.getProductPrice().doubleValue() * purchaseDetail.getQuantity());
            purchaseDetailService.savePurchaseDetail(purchaseDetail);
        }
        return purchase1;
    }

    @Override
    public VendorProductScrapsPurchase getTransactionByiD(String id) {
        if(!purchaseRepository.existsById(id)){
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "product_scraps", id);
            throw new DataNotFoundException(message);
        } else {
            return purchaseRepository.findById(id).get();
        }
    }
}
