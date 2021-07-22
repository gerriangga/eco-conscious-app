package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.Customer;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.exception.DataNotFoundException;
import com.example.EcoConsciousApp.repository.UsableProductRepository;
import com.example.EcoConsciousApp.service.UsableProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsableProductServiceImpl implements UsableProductService {

    @Autowired
    UsableProductRepository usableProductRepository;

    @Override
    public UsableProduct saveUsableProduct(UsableProduct usableProduct) {
        return usableProductRepository.save(usableProduct);
    }

    @Override
    public UsableProduct updateUsableProduct(UsableProduct usableProduct, String id) {
        validatePresent(id);
        UsableProduct usableProductById = usableProductRepository.findById(id).get();
        usableProductById.setUsableProductName(usableProduct.getUsableProductName());
        usableProductById.setUsableProductDescription(usableProduct.getUsableProductDescription());
        usableProductById.setUsableProductPrice(usableProduct.getUsableProductPrice());
        usableProductById.setUsableProductStock(usableProduct.getUsableProductStock());
        return  usableProductRepository.save(usableProductById);
    }

    @Override
    public UsableProduct getUsableProductById(String id) {
        validatePresent(id);
        return usableProductRepository.findById(id).get();
    }

    @Override
    public List<UsableProduct> getAllUsableProduct() {
        return usableProductRepository.findAll();
    }

    @Override
    public void deleteUsableProduct(String id) {
        validatePresent(id);
        usableProductRepository.deleteById(id);
    }

    private void validatePresent(String id) {
        if (!usableProductRepository.findById(id).isPresent()) {
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "usable product", id);
            throw new DataNotFoundException(message);
        }
    }
}