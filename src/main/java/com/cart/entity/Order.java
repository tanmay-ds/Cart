package com.cart.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UserOrder")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userOrderid;
	
	@OneToMany
	@JoinTable(name = "uOrder",joinColumns = @JoinColumn(name = "userOrderid"),inverseJoinColumns = @JoinColumn(name = "orderid"))
	private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

	public Long getUserOrderid() {
		return userOrderid;
	}

	public void setUserOrderid(Long userOrderid) {
		this.userOrderid = userOrderid;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
