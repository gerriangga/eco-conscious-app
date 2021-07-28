package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.constant.ResponseMessage;
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
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportCustomerServiceImpl implements ReportCustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public JasperPrint exportReportPDF(OutputStream outputStream) throws FileNotFoundException, JRException {
        List<Customer> customers = customerRepository.findAll();
        File file = ResourceUtils.getFile("classpath:customers.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Group Five");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        return jasperPrint;
    }

}
