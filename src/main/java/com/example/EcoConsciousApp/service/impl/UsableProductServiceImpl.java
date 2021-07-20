package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.entity.UsableProduct;
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
    public UsableProduct getUsableProductById(String id) {
        return usableProductRepository.findById(id).get();
    }

    @Override
    public List<UsableProduct> getAllUsableProduct() {
        return usableProductRepository.findAll();
    }

    @Override
    public void deleteUsableProduct(String id) {
        usableProductRepository.deleteById(id);
    }
}
