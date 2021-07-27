package com.example.EcoConsciousApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductScrapsSearchDTO {
    private String searchProductName;
    private Integer searchProductPrice;

    public ProductScrapsSearchDTO(String searchProductName, Integer searchProductPrice) {
        this.searchProductName = searchProductName;
        this.searchProductPrice = searchProductPrice;
    }
}
