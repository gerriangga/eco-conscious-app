package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.entity.UsableProduct;

import java.util.List;

public interface UsableProductService {
    public UsableProduct saveUsableProduct(UsableProduct usableProduct);
    public UsableProduct getUsableProductById(String id);
    public List<UsableProduct> getAllUsableProduct();
    public void deleteUsableProduct(String id);
}
