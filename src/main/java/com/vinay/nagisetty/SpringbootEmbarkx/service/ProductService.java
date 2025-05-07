package com.vinay.nagisetty.SpringbootEmbarkx.service;

import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductDto;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductResponseDTO;
import com.vinay.nagisetty.SpringbootEmbarkx.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDto addProduct(ProductDto product, Long categoryId);
//
//    ProductResponseDTO getProducts();

//    ProductResponseDTO getProductsByCategory(Long categoryId);

    ProductResponseDTO searChByProductName(String productName);

    ProductDto updateProduct(Long productId, ProductDto product);

    void deleteProduct(Long productId);

    ProductDto updateProductImage(Long productId, MultipartFile image) throws IOException;

    ProductResponseDTO getProducts(int pageNumber, int pageSize, String sortOrder, String sortByField);

    ProductResponseDTO getProductsByCategory(Long categoryId, int pageNumber, int pageSize, String sortOrder, String sortByField);
}
