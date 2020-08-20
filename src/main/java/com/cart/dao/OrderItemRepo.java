package com.cart.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.entity.OrderItems;

public interface OrderItemRepo extends JpaRepository<OrderItems, Long>{

	List<OrderItems> findByUserId(Long uid, Pageable page);

}
