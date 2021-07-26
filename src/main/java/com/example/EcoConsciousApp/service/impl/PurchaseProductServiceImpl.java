package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.Customer;
import com.example.EcoConsciousApp.entity.PurchaseProduct;
import com.example.EcoConsciousApp.entity.PurchaseProductDetail;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.exception.DataNotFoundException;
import com.example.EcoConsciousApp.repository.PurchaseProductRepository;
import com.example.EcoConsciousApp.service.CustomerService;
import com.example.EcoConsciousApp.service.PurchaseProductDetailService;
import com.example.EcoConsciousApp.service.PurchaseProductService;
import com.example.EcoConsciousApp.service.UsableProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
public class PurchaseProductServiceImpl implements PurchaseProductService {

    @Autowired
    PurchaseProductRepository purchaseProductRepository;

    @Autowired
    PurchaseProductDetailService purchaseProductDetailService;

    @Autowired
    UsableProductService usableProductService;

    @Autowired
    CustomerService customerService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    @Transactional
    public PurchaseProduct transaction(PurchaseProduct purchaseProduct) {
        PurchaseProduct purchaseProductSave = purchaseProductRepository.save(purchaseProduct);
        BigDecimal amount = new BigDecimal(0.0);
        for (PurchaseProductDetail purchaseProductDetail : purchaseProductSave.getPurchaseProductDetails()) {
            UsableProduct usableProduct = usableProductService.getUsableProductById(purchaseProductDetail.getUsableProduct().getId());
            usableProduct.setUsableProductStock(usableProduct.getUsableProductStock() - purchaseProductDetail.getQuantity());
            usableProductService.saveUsableProduct(usableProduct);
            purchaseProductDetail.setPurchaseProduct(purchaseProductSave);

            Double subTotal = usableProduct.getUsableProductPrice().doubleValue() * purchaseProductDetail.getQuantity();
            purchaseProductDetail.setPriceSell(subTotal);
            amount = amount.add(new BigDecimal(subTotal));
            purchaseProductDetailService.savePurchaseProductDetail(purchaseProductDetail);
        }

        Customer customer = customerService.getCustomerById(purchaseProduct.getCustomer().getId());
        String url = "http://localhost:8081/debit";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("phoneNumber", customer.getPhoneNumber())
                .queryParam("amount", amount);
        // http://localhost:8081/debit?phoneNumber=082100000&amount=9000

        restTemplate.exchange(builder.toUriString(), HttpMethod.POST, null, String.class);

        return purchaseProductSave;
    }

    @Override
    public PurchaseProduct getTransactionById(String id) {
        validatePresent(id);
        return purchaseProductRepository.findById(id).get();
    }

    private void validatePresent(String id) {
        if (!purchaseProductRepository.findById(id).isPresent()) {
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "purchase product", id);
            throw new DataNotFoundException(message);
        }
    }
}
