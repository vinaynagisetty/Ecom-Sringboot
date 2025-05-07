package com.vinay.nagisetty.SpringbootEmbarkx.service;

import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductDto;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductResponseDTO;

public interface ProductService {
    ProductDto addProduct(ProductDto product, Long categoryId);

    ProductResponseDTO getProducts();

    ProductResponseDTO getProductsByCategory(Long categoryId);

    ProductResponseDTO searChByProductName(String productName);

    ProductDto updateProduct(Long productId, ProductDto product);

    void deleteProduct(Long productId);
}
