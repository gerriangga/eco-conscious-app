package com.example.EcoConsciousApp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryDTO {

    @NotEmpty(message = "Category name is required")
    private String categoryName;

    @NotEmpty(message = "Category description is required")
    private String categoryDescription;
}
