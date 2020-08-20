package com.cart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
