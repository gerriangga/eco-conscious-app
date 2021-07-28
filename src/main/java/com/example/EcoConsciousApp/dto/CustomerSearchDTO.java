package com.example.EcoConsciousApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CustomerSearchDTO {
    private String searchCustomerFullName;
    private String searchCustomerAddress;
    private String searchCustomerEmail;
}

