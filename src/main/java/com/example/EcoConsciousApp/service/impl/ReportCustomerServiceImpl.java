package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.entity.Customer;
import com.example.EcoConsciousApp.repository.CustomerRepository;
import com.example.EcoConsciousApp.service.ReportCustomerService;
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
public class ReportCustomerServiceImpl implements ReportCustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\ProjectDev\\JavaProjects\\EnigmaCamp\\FinalProject\\eco-conscious-app\\src\\main\\report";
        List<Customer> customers = customerRepository.findAll();
        File file = ResourceUtils.getFile("classpath:customers.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Group Five");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\customers.html");
        }

        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\customers.pdf");
        }
        return "report generated in path : " + path;
    }
}
