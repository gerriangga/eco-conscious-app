package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.dto.UsableProductSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface UsableProductService {
    public UsableProduct saveUsableProduct(UsableProduct usableProduct);

    public UsableProduct getUsableProductById(String id);

    public List<UsableProduct> getAllUsableProducts();

    public void deleteUsableProduct(String id);

    public UsableProduct updateUsableProduct(UsableProduct usableProduct, String id);

    public Page<UsableProduct> getUsableProductPerPage(Pageable pageable, UsableProductSearchDTO usableProductSearchDTO);

    public UsableProduct storeImageFile(MultipartFile multipartFile, String id);

    public UsableProduct getImageFile(String id);

}
