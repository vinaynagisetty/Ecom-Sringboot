package com.vinay.nagisetty.SpringbootEmbarkx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private String productName;
    private String description;
    private double price;
    private String image;
    private int quantity;
    private double discount;
    private double specialPrice;


}
