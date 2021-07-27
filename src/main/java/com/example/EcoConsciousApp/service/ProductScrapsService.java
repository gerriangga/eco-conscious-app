package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.entity.ProductScraps;
import com.example.EcoConsciousApp.entity.Supplier;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

import java.util.List;

public interface ProductScrapsService {
    public ProductScraps saveProductScraps(ProductScraps productScraps);

    public List<ProductScraps> getAllProductScraps();
    public ProductScraps getProductScrapsById(String id);
    public void deleteProductScrapsById(String id);
    public void addSupplier(Supplier supplier, String id);
    public List<ProductScraps> findProductScrapsByCategoryId(String id);
    public Iterable<ProductScraps> findProductScrapsPerPage(String name, Pageable pageable);
    public Iterable<ProductScraps> saveBatchProductScraps(Iterable<ProductScraps> productScraps);

    //Upload & Read CSV Files
    public List<ProductScraps> saveCSV(MultipartFile file);

    //Download Generated Files using Jasper
    public String exportReport(String reportFormat, String searchKey) throws FileNotFoundException, JRException;

    //public Page<ProductScraps> getProductScrapsPerPage(Pageable pageable, ProductScrapsSearchDTO productScrapsSearchDTO);
    public ProductScraps saveImageFile(MultipartFile multipartFile, String id);
    public ProductScraps getImageFile(String id);

}
