package com.vinay.nagisetty.SpringbootEmbarkx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private List<ProductDto> content;
    public Integer pageNumber;
    public  Integer pageSize;
    public Long totalElements;
    public Integer totalPages;
    public boolean lastPage;
}
