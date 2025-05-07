package com.vinay.nagisetty.SpringbootEmbarkx.controller;

import com.vinay.nagisetty.SpringbootEmbarkx.dto.ApiResponse;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductAddResponseDTO;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductDto;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductResponseDTO;
import com.vinay.nagisetty.SpringbootEmbarkx.model.Product;
import com.vinay.nagisetty.SpringbootEmbarkx.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/admin/category/{categoryId}/product")
    public ResponseEntity<ProductAddResponseDTO> addProduct(@RequestBody Product product,
                                                         @PathVariable Long categoryId) {

        ProductDto productResponse= productService.addProduct(product, categoryId);
        ProductAddResponseDTO productAddResponseDTO = modelMapper.map(productResponse, ProductAddResponseDTO.class);


        // Logic to add a product
        return new ResponseEntity<>(productAddResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponseDTO> getProducts() {
        ProductResponseDTO productResponse = productService.getProducts();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/category/{categoryId}/products")
    public ResponseEntity<ProductResponseDTO> getProductsByCategory(@PathVariable Long categoryId) {
        ProductResponseDTO productResponse = productService.getProductsByCategory(categoryId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    @GetMapping("/public/products/search/{productName}")
    public ResponseEntity<ProductResponseDTO> searChByProductName(@PathVariable String productName) {
        ProductResponseDTO productResponse = productService.searChByProductName(productName);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/admin/product/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        ProductDto productResponse = productService.updateProduct(productId, product);
        return new ResponseEntity<>(new ApiResponse("Product updated successfully", true), HttpStatus.OK);
    }
}
