package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.dto.CategoryDTO;
import com.example.EcoConsciousApp.dto.ResponseData;
import com.example.EcoConsciousApp.entity.Category;
import com.example.EcoConsciousApp.service.impl.CategoryServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.CATEGORY)
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryDTO, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.saveCategory(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable("categoryId") String id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> updateCategory(@Valid @RequestBody Category category, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if(!errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(categoryService.saveCategory(category));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategoryById(@PathVariable("categoryId") String id){
        categoryService.deleteCategoryById(id);
    }
}
