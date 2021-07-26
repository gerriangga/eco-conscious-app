package com.example.EcoConsciousApp.utils;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.ProductScraps;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    private static final String TYPE = "text/csv";

    //file validation for CSV
    public static Boolean hasCSVFormat(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    public static List<ProductScraps> csvToProductScraps(InputStream inputStream){
        try{
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT
                                                                    .withFirstRecordAsHeader()
                                                                    .withIgnoreHeaderCase()
                                                                    .withTrim());
            List<ProductScraps> productScraps = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                ProductScraps productScrap = new ProductScraps();
                productScrap.setProductName(csvRecord.get("ProductName"));
                productScrap.setProductDescription(csvRecord.get("ProductDescription"));
                productScrap.setUploadDate(Date.valueOf(csvRecord.get("UploadDate")));
                productScrap.setProductPrice(Integer.parseInt(csvRecord.get("ProductPrice")));
                productScrap.setStock(Integer.parseInt(csvRecord.get("Stock")));
                productScrap.setIsDeleted(Boolean.parseBoolean(csvRecord.get("isDeleted")));
                productScraps.add(productScrap);
            }
            csvParser.close();
            return productScraps;
        }catch(IOException ex){
            throw new RuntimeException(ResponseMessage.FAILED_PARSE_CSV_FILE + ex.getMessage());
        }
    }

}


