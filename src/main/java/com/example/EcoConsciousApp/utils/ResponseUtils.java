package com.example.EcoConsciousApp.utils;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseUtils {
    private Date timestamp;
    private Integer statusCode;
    private String message;


}
