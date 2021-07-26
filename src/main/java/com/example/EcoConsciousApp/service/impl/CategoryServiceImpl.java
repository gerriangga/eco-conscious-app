package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.entity.Category;
import com.example.EcoConsciousApp.repository.CategoryRepository;
import com.example.EcoConsciousApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(String id) {
        Optional<Category> category = categoryRepository.findById(id); //checking using optional
        if(!category.isPresent()) //validate
            return null;
        return category.get();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryById(String id) {
        Category category = getCategoryById(id);
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }
}
