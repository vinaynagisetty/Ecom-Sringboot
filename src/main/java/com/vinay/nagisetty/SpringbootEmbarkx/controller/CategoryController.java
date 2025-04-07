package com.vinay.nagisetty.SpringbootEmbarkx.controller;

import com.vinay.nagisetty.SpringbootEmbarkx.config.AppConstants;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.CategoryRequestDto;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.CategoryResponseDto;
import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import com.vinay.nagisetty.SpringbootEmbarkx.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {


     private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponseDto>getCategories(@RequestParam(name="pageNumber",defaultValue = AppConstants.pageNumber,required = false)int pageNumber,
                                                            @RequestParam(name="pageSize",defaultValue = AppConstants.pageSize,required = false)int pageSize,
                                                            @RequestParam (name="sort_order", defaultValue = AppConstants.sort_order,required = false) String sort_order,
                                                            @RequestParam(name = "sort_by_field",defaultValue = AppConstants.sort_by_field,required = false)String sort_by_field) {
        CategoryResponseDto categories = categoryService.getCategories(pageNumber,pageSize,sort_order,sort_by_field);
        return  ResponseEntity.ok(categories);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryRequestDto> addCategory( @Valid @RequestBody CategoryRequestDto category) {

        CategoryRequestDto s = categoryService.addCategory(category);
        return new ResponseEntity<>(s,HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryRequestDto>  deleteCategory(@PathVariable Long categoryId) {

        CategoryRequestDto status=categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status,HttpStatus.OK);

    }
    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryRequestDto> updateCategory(@RequestBody CategoryRequestDto  categoryRequestDto,
                                                 @PathVariable Long categoryId){

        CategoryRequestDto s=categoryService.updateCategory(categoryRequestDto,categoryId);
            return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
