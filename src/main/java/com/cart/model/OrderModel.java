package com.cart.model;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
	private Long userOrderid;
	private List<OrderDetailsModel> orderDetailsModels = new ArrayList<OrderDetailsModel>();
	public Long getUserOrderid() {
		return userOrderid;
	}
	public void setUserOrderid(Long userOrderid) {
		this.userOrderid = userOrderid;
	}
	public List<OrderDetailsModel> getOrderDetailsModels() {
		return orderDetailsModels;
	}
	public void setOrderDetailsModels(List<OrderDetailsModel> orderDetailsModels) {
		this.orderDetailsModels = orderDetailsModels;
	}
	
	
}
