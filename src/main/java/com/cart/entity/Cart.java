package com.cart.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cid;
	
	@ManyToMany
	@JoinTable(name = "cart_items",joinColumns = @JoinColumn(name = "cid"),inverseJoinColumns = @JoinColumn(name = "productid"))
	private List<Product> cartItems = new ArrayList<>();

	
	
	public List<Product> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<Product> cartItems) {
		this.cartItems = cartItems;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}
	
	
}
