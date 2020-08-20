package com.cart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.OrderDetails;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long>{

}
