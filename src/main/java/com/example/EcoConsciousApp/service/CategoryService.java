package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.entity.Category;

import java.util.List;

public interface CategoryService {
    public Category saveCategory(Category category);
    public Category getCategoryById(String id);
    public List<Category> getAllCategory();
    public void deleteCategoryById(String id);
}
