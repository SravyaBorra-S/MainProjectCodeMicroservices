package com.product.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.product.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
//	List<Product> findAll();
//
//	Product save(Product pro);
}

