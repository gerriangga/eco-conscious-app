package com.example.EcoConsciousApp.service;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface ReportUsableProductService {
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException;
}
