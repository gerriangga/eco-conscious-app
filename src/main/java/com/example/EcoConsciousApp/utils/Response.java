package com.example.EcoConsciousApp.utils;

import lombok.Data;

import java.util.Date;

@Data
public class Response<T> {
    private Date timestamp;
    private String message;
    private T data;
}
