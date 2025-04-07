package com.vinay.nagisetty.SpringbootEmbarkx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  CategoryResponseDto {
    List<CategoryRequestDto> contents;
    public Integer pageNumber;
    public  Integer pageSize;
    public Long totalElements;
    public Integer totalPages;
    public boolean lastPage;


}
