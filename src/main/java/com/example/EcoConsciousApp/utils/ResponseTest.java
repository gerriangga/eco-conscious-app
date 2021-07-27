package com.example.EcoConsciousApp.utils;

import lombok.Data;

@Data
public class ResponseTest<T> {
    private String service;
    private String message;
    private T data;
}
