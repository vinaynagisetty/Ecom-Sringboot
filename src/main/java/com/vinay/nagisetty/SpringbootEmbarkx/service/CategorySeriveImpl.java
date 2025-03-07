package com.vinay.nagisetty.SpringbootEmbarkx.service;

import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorySeriveImpl implements CategoryService {
    public List<Category> categories = new ArrayList();
    private Long categoryId = 1L;

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String addCategory(Category category) {
        category.setCategoryId(categoryId++);
        categories.add(category);
        return "Category added successfully";
    }

    @Override
    public String deleteCategory(Long categoryId) {
       Category category= categories.stream().filter(value ->value.getCategoryId().equals(categoryId)).findFirst().orElse(null);
        if(category == null){
            return "Category with categoryId " + categoryId + " not found";
        }
        categories.remove(category);
        return "Category with categoryId " + categoryId + " deleted successfully";
    }
}
