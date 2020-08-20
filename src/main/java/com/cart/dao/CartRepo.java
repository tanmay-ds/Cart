package com.cart.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{
	
}
