package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.UsableProductSearchDTO;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.exception.DataNotFoundException;
import com.example.EcoConsciousApp.repository.UsableProductRepository;
import com.example.EcoConsciousApp.service.UsableProductService;
import com.example.EcoConsciousApp.specification.UsableProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<UsableProduct> getAllUsableProducts() {
        return usableProductRepository.findAll();
    }

    @Override
    public Page<UsableProduct> getUsableProductPerPage(Pageable pageable, UsableProductSearchDTO usableProductSearchDTO) {
        Specification<UsableProduct> usableProductSpecification = UsableProductSpecification.getSpecification(usableProductSearchDTO);
        return usableProductRepository.findAll(usableProductSpecification, pageable);
    }

    @Override
    public void deleteUsableProduct(String id) {
        validatePresent(id);
        usableProductRepository.deleteById(id);
    }

    @Override
    public UsableProduct storeImageFile(MultipartFile multipartFile, String id) {
        validatePresent(id);
        UsableProduct usableProductById = usableProductRepository.findById(id).get();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        usableProductById.setUsableProductImage(fileName);

        try {
            usableProductById.setData(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usableProductRepository.save(usableProductById);
    }

    @Override
    public UsableProduct getImageFile(String id) {
        validatePresent(id);
        return usableProductRepository.findById(id).get();
    }

    private void validatePresent(String id) {
        if (!usableProductRepository.findById(id).isPresent()) {
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "usable product", id);
            throw new DataNotFoundException(message);
        }
    }
}