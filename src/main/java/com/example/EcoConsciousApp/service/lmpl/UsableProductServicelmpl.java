package com.example.EcoConsciousApp.service.lmpl;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.exception.DataNotFoundException;
import com.example.EcoConsciousApp.repository.UsableProductRepository;
import com.example.EcoConsciousApp.service.UsableProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsableProductServicelmpl implements UsableProductService {
    @Autowired
    UsableProductRepository usableProductRepository;

    @Override
    public UsableProduct saveUsableProduct(UsableProduct usableProduct) {
        return usableProductRepository.save(usableProduct);
    }

    @Override
    public UsableProduct getUsableProductById(String id) {
        if(!usableProductRepository.existsById(id)){
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "usable_products", id);
            throw new DataNotFoundException(message);
        } else {
            return usableProductRepository.findById(id).get();
        }
    }

    @Override
    public List<UsableProduct> getAllUsableProducts() {
        return usableProductRepository.findAll();
    }

    @Override
    public void deleteUsableProduct(String id) {
        usableProductRepository.deleteById(id);
    }

}
