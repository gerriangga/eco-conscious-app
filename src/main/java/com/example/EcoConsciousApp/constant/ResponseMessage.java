package com.example.EcoConsciousApp.constant;

public class ResponseMessage {

    //General
    public static final String NOT_FOUND = "Not Found";
    public static final String PATH = "E:\\EnigmaCamp\\Final Project";

    //Product Scraps Get All Data Controller
    public static final String GET_PRODUCT_SCRAPS = "Get all Product Scraps";
    public static final String CANT_GET_PRODUCT_SCRAPS = "Cannot get Product Scraps : ";

    //Product Scraps Upload or Add File CSV
    public static final String PLEASE_UPLOAD_CSV_FILE = "Please upload the corresponding file (CSV) : ";
    public static final String UPLOAD_SUCCESS = "File has been uploaded successfully";
    public static final String CANT_UPLOAD_FILE = "Could not upload the file : ";
    public static final String FAILED_UPLOAD = "Failed to store CSV data : ";
    public static final String FAILED_PARSE_CSV_FILE = "Failed to parse CSV File : ";

    //Product Scraps Add Supplier Service Impl
    public static final String PRODUCT_ID = "Product Scraps with ID : ";

    //Authentication
    public static final String INVALID_USERNAME_OR_PASSWORD = "Invalid username or password";
    public static final String USERNAME_HAS_BEEN_TAKEN = "Username has exist, please input another username";
    public static final String USER_SUCCESSFULLY_REGISTERED = "You have successfully registered!";

    //Jasper Report
    public static final String JASPER_REPORT_FILE_LOCATION = "classpath:ProductScrapsReport.jrxml";
    public static final String REPORT_GENERATED = "Report Generated in Path : ";
    public static final String PRODUCT_SCRAPS_FILE_NAME_HTML = "\\ProductScrapsReport.html";
    public static final String PRODUCT_SCRAPS_FILE_NAME_PDF = "\\ProductScrapsReport.pdf";
}

