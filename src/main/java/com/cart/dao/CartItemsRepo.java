package com.cart.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.CartItems;
@Repository
public interface CartItemsRepo extends JpaRepository<CartItems, Long>{

	List<CartItems> findByUserId(Long uid, Pageable page);

	CartItems findByProductId(Long pid);

	CartItems findByUserIdAndProductId(Long uid, Long pid);
	
}
