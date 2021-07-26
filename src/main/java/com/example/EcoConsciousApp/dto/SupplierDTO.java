package com.example.EcoConsciousApp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class SupplierDTO {

    @NotEmpty(message = "Full name is required")
    private String fullName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid, please retype your email")
    private String email;

    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;
}
