package com.vinay.nagisetty.SpringbootEmbarkx.service;

import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> getCategories();
    String addCategory(Category category);

    String deleteCategory(Long categoryId);

    String updateCategory(Category catogoty, Long categoryId);
}
