package com.vinay.nagisetty.SpringbootEmbarkx.controller;

import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import com.vinay.nagisetty.SpringbootEmbarkx.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {


     private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/api/admin/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories() ;
    }

    @PostMapping("/api/admin/categories")
    public String addCategory(@RequestBody Category category) {

        return  categoryService.addCategory(category);
    }
}
