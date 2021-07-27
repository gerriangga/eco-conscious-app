package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.entity.Vendor;
import com.example.EcoConsciousApp.repository.VendorRepository;
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
public class ReportService {

    @Autowired
    private VendorRepository repository;

    public String exportReport(String reportFormat, List<Vendor> vendors) throws FileNotFoundException, JRException {
//        List<Vendor> vendors= repository.findAll();
        String path = "C:\\Users\\meggy\\Desktop";
        File file = ResourceUtils.getFile("classpath:Vendor.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vendors);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Mega Silvia");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if(reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\vendors.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+ "\\vendors.pdf");
        }

        return "report generated in path : " + path;

    }
}
