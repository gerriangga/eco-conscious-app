package com.example.EcoConsciousApp.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String id) {
        super("Customer id not found : " + id);
    }
}
