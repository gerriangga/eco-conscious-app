package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.dto.ProductScrapsSearchDTO;
import com.example.EcoConsciousApp.entity.ProductScraps;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductScrapsService {
    public ProductScraps saveProductScraps(ProductScraps productScraps);
    public ProductScraps getProductScrapsById(String id);
    public List<ProductScraps> getAllProductScraps();
    public void deleteProductScraps(String id);
    public Page<ProductScraps> getProductScrapsPerPage(Pageable pageable, ProductScrapsSearchDTO productScrapsSearchDTO);
    public List<ProductScraps> getProductScrapsByName(String name);
    public ProductScraps saveImageFile(MultipartFile multipartFile, String id);
    public ProductScraps getImageFile(String id);
}
