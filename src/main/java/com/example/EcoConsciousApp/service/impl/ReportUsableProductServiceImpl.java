package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.constant.PathConstant;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.repository.UsableProductRepository;
import com.example.EcoConsciousApp.service.ReportUsableProductService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportUsableProductServiceImpl implements ReportUsableProductService {

    @Autowired
    UsableProductRepository usableProductRepository;

    @Override
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<UsableProduct> usableProducts = usableProductRepository.findAll();
        File file = ResourceUtils.getFile("classpath:usableproducts.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(usableProducts);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Group Five");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, PathConstant.PATH_REPORT + "\\usable-products.html");
        }

        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, PathConstant.PATH_REPORT + "\\usable-products.pdf");
        }
        return "report generated in path : " + PathConstant.PATH_REPORT;
    }
}
