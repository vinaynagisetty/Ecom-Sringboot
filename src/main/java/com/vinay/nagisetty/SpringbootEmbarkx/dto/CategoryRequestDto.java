package com.vinay.nagisetty.SpringbootEmbarkx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {
    private Long categoryId;
    @NotBlank
    @Size(min=5,max = 10,message = "Category name must be in between 5 to 10 characters only")
    private String categoryName;
}
