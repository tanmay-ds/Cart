package com.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cart.dao.OrderItemRepo;
import com.cart.entity.OrderItems;
import com.cart.model.OrderItemModel;

@Service
public class OrderService {
	@Autowired
	OrderItemRepo orderItemRepo;

	public List<OrderItemModel> getUserOrderList(Long uid,Pageable page) {
		List<OrderItems> orderItems = orderItemRepo.findByUserId(uid,page);
		List<OrderItemModel> orderItemModels = new ArrayList<OrderItemModel>();
		
		for(OrderItems orderItem:orderItems) {
			OrderItemModel orderItemModel = new OrderItemModel();
			orderItemModel.setOrderItemid(orderItem.getOrderItemid());
			orderItemModel.setOrderId(orderItem.getOrderId());
			orderItemModel.setProductName(orderItem.getProductName());
			orderItemModel.setColor(orderItem.getColor());
			orderItemModel.setPrice(orderItem.getPrice());
			orderItemModel.setCategory(orderItem.getCategory());
			orderItemModel.setUserId(uid);
			orderItemModels.add(orderItemModel);
		}
		
		return orderItemModels;
	}

}
