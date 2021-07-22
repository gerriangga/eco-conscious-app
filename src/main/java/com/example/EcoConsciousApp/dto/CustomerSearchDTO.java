package com.example.EcoConsciousApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CustomerSearchDTO {
    private String searchCustomerFirstName;
    private String searchCustomerLastName;
    private String searchCustomerEmail;
}
