package com.vinay.nagisetty.SpringbootEmbarkx.repository;

import com.vinay.nagisetty.SpringbootEmbarkx.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
