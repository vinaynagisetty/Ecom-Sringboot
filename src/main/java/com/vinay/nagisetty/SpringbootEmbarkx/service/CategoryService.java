package com.vinay.nagisetty.SpringbootEmbarkx.service;

import com.vinay.nagisetty.SpringbootEmbarkx.dto.CategoryRequestDto;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.CategoryResponseDto;
import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    CategoryResponseDto getCategories(int pageNumber,int pageSize,String sort_order,String sort_by_field);
    CategoryRequestDto addCategory(CategoryRequestDto categorydto);

    CategoryRequestDto deleteCategory(Long categoryId);

    CategoryRequestDto updateCategory(CategoryRequestDto catogotyDto, Long categoryId);
}
