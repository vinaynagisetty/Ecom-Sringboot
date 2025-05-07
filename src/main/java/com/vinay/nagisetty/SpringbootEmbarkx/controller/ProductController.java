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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseEntity<ProductAddResponseDTO> addProduct(@RequestBody ProductDto productDto,
                                                         @PathVariable Long categoryId) {

        ProductDto productResponse= productService.addProduct(productDto, categoryId);
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
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        ProductDto productResponse = productService.updateProduct(productId, productDto);
        return new ResponseEntity<>(new ApiResponse("Product updated successfully", true), HttpStatus.OK);
    }

    @DeleteMapping("/admin/product/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new ApiResponse("Product deleted successfully", true), HttpStatus.OK);
    }
    @PutMapping("/admin/product/{productId}/image")
    public ResponseEntity<ApiResponse> updateProdcutImage(@PathVariable Long productId, @RequestParam("image") MultipartFile image) throws IOException {
        ProductDto product = productService.updateProductImage(productId, image);
        return new ResponseEntity<>(new ApiResponse("Product image updated successfully", true), HttpStatus.OK);
    }


}
