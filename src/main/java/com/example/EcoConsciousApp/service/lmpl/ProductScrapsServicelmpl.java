package com.example.EcoConsciousApp.service.lmpl;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.ProductScrapsSearchDTO;
import com.example.EcoConsciousApp.entity.ProductScraps;
import com.example.EcoConsciousApp.exception.DataNotFoundException;
import com.example.EcoConsciousApp.repository.ProductScrapsRepository;
import com.example.EcoConsciousApp.service.ProductScrapsService;
import com.example.EcoConsciousApp.specification.ProductScrapsSpecification;
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
public class ProductScrapsServicelmpl implements ProductScrapsService {
    @Autowired
    ProductScrapsRepository productScrapsRepository;

    @Override
    public ProductScraps saveProductScraps(ProductScraps productScraps) {
        return productScrapsRepository.save(productScraps);
    }

    @Override
    public ProductScraps getProductScrapsById(String id) {
        validatePresent(id);
        return productScrapsRepository.findById(id).get();
    }

    @Override
    public List<ProductScraps> getAllProductScraps() { return productScrapsRepository.findAll();
    }

    @Override
    public void deleteProductScraps(String id) {
        validatePresent(id);
        productScrapsRepository.deleteById(id);
    }

    @Override
    public Page<ProductScraps> getProductScrapsPerPage(Pageable pageable, ProductScrapsSearchDTO productScrapsSearchDTO) {
        Specification<ProductScraps> productScrapsSpecification = ProductScrapsSpecification.
                getSpecification(productScrapsSearchDTO);
        return productScrapsRepository.findAll(productScrapsSpecification, pageable);

    }

    @Override
    public List<ProductScraps> getProductScrapsByName(String name) {
        return productScrapsRepository.findProductScrapsByProductNameContainingIgnoreCase(name);
    }

    @Override
    public ProductScraps saveImageFile(MultipartFile multipartFile, String id) {
        validatePresent(id);
        ProductScraps productScraps = productScrapsRepository.findById(id).get();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        productScraps.setProductScrapsImage(fileName);

        try {
            productScraps.setData(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productScrapsRepository.save(productScraps);
    }

    @Override
    public ProductScraps getImageFile(String id) {
        validatePresent(id);
        return productScrapsRepository.findById(id).get();
    }

    private void validatePresent(String id) {
        if (!productScrapsRepository.findById(id).isPresent()){
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "product_scraps", id);
            throw new DataNotFoundException(message);
        }

    }
}
