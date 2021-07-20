package com.example.EcoConsciousApp.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Integer id) {
        super("Customer id not found : " + id);
    }
}
