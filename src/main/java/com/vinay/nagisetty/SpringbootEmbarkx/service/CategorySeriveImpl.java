package com.vinay.nagisetty.SpringbootEmbarkx.service;

import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import com.vinay.nagisetty.SpringbootEmbarkx.repository.ICategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorySeriveImpl implements CategoryService {
//    public List<Category> categories = new ArrayList();
//    private Long categoryId = 1L;

private ICategoryRepository categoryRepository;

    public CategorySeriveImpl(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public String addCategory(Category category) {
//        category.setCategoryId(categoryId++);
        categoryRepository.save(category);
        return "Category added successfully";
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category=categoryRepository.findById(categoryId).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with categoryId " + categoryId + " not found")
        );
//        List<Category> categories=categoryRepository.findAll();
//        Category category= categories.stream().filter(value ->value.getCategoryId().equals(categoryId)).findFirst().orElseThrow(
//               ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with categoryId " + categoryId + " not found")
//       );

        categoryRepository.delete(category);
        return "Category with categoryId " + categoryId + " deleted successfully";
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {
//        List<Category> categories=categoryRepository.findAll();
//
//        Category category1= categories.stream().filter(value ->value.getCategoryId().equals(categoryId)).findFirst().orElseThrow(
//                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with categoryId " + categoryId + " not found")
//        );
//        category1.setCategoryName(category.getCategoryName());
        Category savedcategory=categoryRepository.findById(categoryId).orElseThrow(  ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with categoryId " + categoryId + " not found")
        );
        category.setCategoryId(categoryId);
        Category savedCategory=categoryRepository.save(category);
        return "Category updated successfully";
    }
}
