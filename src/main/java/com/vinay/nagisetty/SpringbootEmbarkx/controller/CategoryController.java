package com.vinay.nagisetty.SpringbootEmbarkx.controller;

import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import com.vinay.nagisetty.SpringbootEmbarkx.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {


     private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>>getCategories() {
        List<Category> categories = categoryService.getCategories();
        return  ResponseEntity.ok(categories);
    }

    @PostMapping("/api/admin/categories")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {

        String s = categoryService.addCategory(category);
        return new ResponseEntity<>(s,HttpStatus.CREATED);
    }
    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String>  deleteCategory(@PathVariable Long categoryId) {
        try {
           String status=categoryService.deleteCategory(categoryId);

            return new ResponseEntity<>(status,HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getMessage(),e.getStatusCode()) ;
        }

    }
}
