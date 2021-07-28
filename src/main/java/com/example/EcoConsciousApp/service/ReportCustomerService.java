package com.example.EcoConsciousApp.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;
import java.io.OutputStream;

public interface ReportCustomerService {
    public JasperPrint exportReportPDF(OutputStream outputStream) throws FileNotFoundException, JRException;
}
