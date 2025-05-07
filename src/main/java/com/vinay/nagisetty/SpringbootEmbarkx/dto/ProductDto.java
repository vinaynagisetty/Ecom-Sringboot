package com.vinay.nagisetty.SpringbootEmbarkx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    @NotBlank
    @Size(min = 3, message = "Product name should be at least 3 characters long")
    private String productName;
    private String description;
    private double price;
    private String image;
    private int quantity;
    private double discount;
    private double specialPrice;


}
