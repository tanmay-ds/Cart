package com.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cart.model.OrderItemModel;
import com.cart.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@GetMapping("user/{uid}/order")
	public List<OrderItemModel> getUserOrderList(@PathVariable Long uid,Pageable pageable){
		return orderService.getUserOrderList(uid,pageable);
	}
}
