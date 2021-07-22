package com.example.EcoConsciousApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UsableProductSearchDTO {
    private String searchUsableProductName;
    private String searchUsableProductDescription;
}
