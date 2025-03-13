package com.vinay.nagisetty.SpringbootEmbarkx.repository;

import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(@NotBlank @Size(min=5,max = 10,message = "Category name must be in between 5 to 10 characters only") String categoryName);
}
