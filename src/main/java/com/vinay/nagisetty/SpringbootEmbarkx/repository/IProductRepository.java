package com.vinay.nagisetty.SpringbootEmbarkx.repository;

import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import com.vinay.nagisetty.SpringbootEmbarkx.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);


    Page<Product> findByProductNameLikeIgnoreCase(String productName, Pageable pageDetails);

    Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pageDetails);
}
