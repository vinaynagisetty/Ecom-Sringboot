package com.vinay.nagisetty.SpringbootEmbarkx.repository;

import com.vinay.nagisetty.SpringbootEmbarkx.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

}
