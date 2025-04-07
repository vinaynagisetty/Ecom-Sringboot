package com.vinay.nagisetty.SpringbootEmbarkx.service;

import com.vinay.nagisetty.SpringbootEmbarkx.dto.CategoryRequestDto;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.CategoryResponseDto;
import com.vinay.nagisetty.SpringbootEmbarkx.exceptions.APIException;
import com.vinay.nagisetty.SpringbootEmbarkx.exceptions.ResourceNotFoundException;
import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import com.vinay.nagisetty.SpringbootEmbarkx.repository.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
private ModelMapper modelMapper;

    public CategorySeriveImpl(ICategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper=modelMapper;

    }

    @Override
    public CategoryResponseDto getCategories(int pageNumber,int pageSize) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<Category> pages=categoryRepository.findAll(pageable);
        List<Category> totalCategory=pages.getContent();

        if(totalCategory.isEmpty()){
            throw new APIException("Doesn't have any category");

        }
        List<CategoryRequestDto> categories=totalCategory.stream().map(
                (category)->modelMapper.map(category,CategoryRequestDto.class)
        ).toList();
        CategoryResponseDto response=new CategoryResponseDto();
        response.setContents(categories);
        response.setPageNumber(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setTotalElements(pages.getTotalElements());
        response.setTotalPages(pages.getTotalPages());
        response.setLastPage(pages.isLast());
        return response;
    }

    @Override
    public CategoryRequestDto addCategory(CategoryRequestDto categorydto) {
//        category.setCategoryId(categoryId++);
        Category category=modelMapper.map(categorydto,Category.class);
        Category categoryFromDb=categoryRepository.findByCategoryName(category.getCategoryName());

        if(categoryFromDb !=null){
            throw new APIException("category with category name"+" "+category.getCategoryName()+" "+" already exists");
        }
//        CategoryRequestDto categoryDto=
        Category savedCategory= categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryRequestDto.class);
    }

    @Override
    public CategoryRequestDto deleteCategory(Long categoryId) {

        Category category=categoryRepository.findById(categoryId).orElseThrow(
                ()->new ResourceNotFoundException("Category","Category Id",categoryId)
        );

        categoryRepository.delete(category);
        return modelMapper.map(category,CategoryRequestDto.class);
    }

    @Override
    public CategoryRequestDto updateCategory(CategoryRequestDto categoryDto, Long categoryId) {
//
        Category category=modelMapper.map(categoryDto,Category.class);
        Category savedcategory=categoryRepository.findById(categoryId).orElseThrow(
                ()->new ResourceNotFoundException("Category","Category Id",categoryId)
        );
        category.setCategoryId(categoryId);
        Category savedCategory=categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryRequestDto.class);
    }
}
