package com.vinay.nagisetty.SpringbootEmbarkx.service;

import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductDto;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.ProductResponseDTO;
import com.vinay.nagisetty.SpringbootEmbarkx.exceptions.APIException;
import com.vinay.nagisetty.SpringbootEmbarkx.exceptions.ResourceNotFoundException;
import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import com.vinay.nagisetty.SpringbootEmbarkx.model.Product;
import com.vinay.nagisetty.SpringbootEmbarkx.repository.ICategoryRepository;
import com.vinay.nagisetty.SpringbootEmbarkx.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

 private final IProductRepository productRepository;
 private final ICategoryRepository categoryRepository;
    private ModelMapper modelMapper;

  @Value("${project.path}")
 private String path;
private final FileServiceImpl fileService;
    public ProductServiceImpl(IProductRepository productRepository, ICategoryRepository categoryRepository,
                              ModelMapper modelMapper, FileServiceImpl fileService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","category id", categoryId));

        boolean isProductExists = true;
        List<Product> products =  category.getProducts();
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productDto.getProductName())) {
                isProductExists = false;
                break;
            }
        }
        if (!isProductExists) {
          throw  new APIException("Product already exists in this category");
        }

        Product product = modelMapper.map(productDto, Product.class);
        product.setCategory(category);
        product.setImage("default.png");
        double specialPrice = product.getPrice() - (product.getPrice() * product.getDiscount() / 100);
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);


        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductResponseDTO getProducts(int pageNumber, int pageSize, String sortOrder, String sortByField) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortByField).ascending()
                : Sort.by(sortByField).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Product> pageProducts = productRepository.findAll(pageDetails);

        List<Product> products = pageProducts.getContent();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setContent(productDtos);
        productResponseDTO.setPageNumber(pageProducts.getNumber());
        productResponseDTO.setPageSize(pageProducts.getSize());
        productResponseDTO.setTotalElements(pageProducts.getTotalElements());
        productResponseDTO.setTotalPages(pageProducts.getTotalPages());
        productResponseDTO.setLastPage(pageProducts.isLast());
        return productResponseDTO;

    }

    @Override
    public ProductResponseDTO getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        List<Product> products = productRepository.findByCategory(category);
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setContent(productDtos);
        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO searChByProductName(String productName) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase("%"+productName+"%");
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setContent(productDtos);
        return productResponseDTO;
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "product id", productId));
        Product product = modelMapper.map(productDto, Product.class);

        existingProduct.setProductName(product.getProductName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setDescription(product.getDescription());
       // existingProduct.setImage(product.getImage());
        existingProduct.setSpecialPrice(product.getPrice() - (product.getPrice() * product.getDiscount() / 100));
        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDto.class);


    }

    @Override
    public void deleteProduct(Long productId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "product id", productId));
        productRepository.delete(existingProduct);
    }


    @Override
    public ProductDto updateProductImage(Long productId, MultipartFile image) throws IOException {
        // Get the product from DB
        Product productFromDb = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        // Upload image to server
        // Get the file name of uploaded image

        String fileName = fileService.uploadImage(path, image);

        // Updating the new file name to the product
        productFromDb.setImage(fileName);

        // Save updated product
        Product updatedProduct = productRepository.save(productFromDb);

        // return DTO after mapping product to DTO
        return modelMapper.map(updatedProduct, ProductDto.class);
    }

}
