package com.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.model.CartItemModel;
import com.cart.model.ProductModel;
import com.cart.service.CartService;

@RestController
public class CartController {
	@Autowired
	CartService cartService;
	
	@GetMapping("user/{uid}/getcart")
	public List<CartItemModel> getUserCartItems(@PathVariable Long uid,Pageable pageable) {
		return cartService.getUserCartItems(uid,pageable);
	}
	
	@GetMapping("user/{uid}/searchitems/{pid}")
	public ProductModel searchUserCartItems(@PathVariable Long uid,@PathVariable Long pid) {
		return cartService.searchUserCartItems(uid,pid);
	}
	
	@PostMapping("user/{uid}/additem/{pid}")
	public String addtoCart(@PathVariable Long uid,@PathVariable Long pid) {
		return cartService.addtoCart(uid,pid);
	}
	
	@DeleteMapping("user/{uid}/removeitem/{pid}")
	public String removeItem(@PathVariable Long uid,@PathVariable Long pid) {
		return cartService.removeItem(uid,pid);
	}
	
	@PostMapping("user/{uid}/submit")
	public String submitItems(@PathVariable Long uid) {
		return cartService.submitItems(uid);
	}
}
