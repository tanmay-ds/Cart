package com.cart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.Order;
@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

}
