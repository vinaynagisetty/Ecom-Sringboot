package com.vinay.nagisetty.SpringbootEmbarkx.service;

import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductDto;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductResponseDTO;
import com.vinay.nagisetty.SpringbootEmbarkx.model.Product;

public interface ProductService {
    ProductDto addProduct(Product product, Long categoryId);

    ProductResponseDTO getProducts();
}
