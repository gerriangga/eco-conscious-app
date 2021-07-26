package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.ProductScraps;
import com.example.EcoConsciousApp.entity.Supplier;
import com.example.EcoConsciousApp.repository.ProductScrapsRepository;
import com.example.EcoConsciousApp.service.ProductScrapsService;
import com.example.EcoConsciousApp.utils.CSVHelper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ProductScrapsServiceImpl implements ProductScrapsService {

    @Autowired
    ProductScrapsRepository productScrapsRepository;

    @Override
    public ProductScraps saveProductScraps(ProductScraps productScraps) {
        return productScrapsRepository.save(productScraps);
    }

    @Override
    public List<ProductScraps> getAllProductScraps() {
        return productScrapsRepository.findAll();
    }

    @Override
    public ProductScraps getProductScrapsById(String id) {
        Optional<ProductScraps> temp = productScrapsRepository.findById(id);
        if(!temp.isPresent())
            return null;
        return productScrapsRepository.findById(id).get();
    }

    @Override
    public void deleteProductScrapsById(String id) {
        ProductScraps productScraps = getProductScrapsById(id);
        productScraps.setIsDeleted(true);
        productScrapsRepository.save(productScraps);
    }

    @Override
    public void addSupplier(Supplier supplier, String id) {
        ProductScraps productScraps = getProductScrapsById(id);
        if(productScraps == null)
            throw new RuntimeException(ResponseMessage.PRODUCT_ID + id + ResponseMessage.NOT_FOUND);
        productScraps.getSuppliers().add(supplier); //bi-directional transaction. Means, if we input a data of Supplier
                                                    //on Product entity, the supplier entity also got inputted. (many-to-many).
        saveProductScraps(productScraps);
    }

    @Override
    public List<ProductScraps> findProductScrapsByCategoryId(String id){
        return productScrapsRepository.getProductScrapsByCategoryId(id);
    }

    @Override
    public Iterable<ProductScraps> findProductScrapsPerPage(String name, Pageable pageable) {
        return productScrapsRepository.findByProductNameContains(name, pageable);
    }

    @Override
    public Iterable<ProductScraps> saveBatchProductScraps(Iterable<ProductScraps> productScraps) {
        return productScrapsRepository.saveAll(productScraps);
    }

    @Override
    public List<ProductScraps> saveCSV(MultipartFile file) {
        try {
            List<ProductScraps> productScraps = CSVHelper.csvToProductScraps(file.getInputStream());
            return productScrapsRepository.saveAll(productScraps);
        }catch(IOException ex){
            throw new RuntimeException(ResponseMessage.FAILED_UPLOAD + ex.getMessage());
        }
    }

    @Override
    public String exportReport(String reportFormat, String searchKey) throws FileNotFoundException, JRException {
        //filtering data
        List<ProductScraps> productScraps = productScrapsRepository.findProductScrapsByProductNameContainingIgnoreCase(searchKey);

        //load and and compile it
        File file = ResourceUtils.getFile(ResponseMessage.JASPER_REPORT_FILE_LOCATION);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("created by", "Eco-Conscious App");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productScraps);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, ResponseMessage.PATH + ResponseMessage.PRODUCT_SCRAPS_FILE_NAME_HTML);
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, ResponseMessage.PATH + ResponseMessage.PRODUCT_SCRAPS_FILE_NAME_PDF);
        }

        return ResponseMessage.REPORT_GENERATED + ResponseMessage.PATH;
    }

}
