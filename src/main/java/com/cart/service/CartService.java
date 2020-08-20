package com.cart.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cart.dao.CartItemsRepo;
import com.cart.dao.CartRepo;
import com.cart.dao.OrderDetailsRepo;
import com.cart.dao.OrderItemRepo;
import com.cart.dao.OrderRepo;
import com.cart.dao.ProductRepo;
import com.cart.dao.UserRepo;
import com.cart.entity.Cart;
import com.cart.entity.CartItems;
import com.cart.entity.Order;
import com.cart.entity.OrderDetails;
import com.cart.entity.OrderItems;
import com.cart.entity.Product;
import com.cart.entity.User;
import com.cart.model.CartItemModel;
import com.cart.model.ProductModel;

@Service
public class CartService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired 
	CartRepo cartRepo;
	
	@Autowired 
	ProductRepo productRepo;
	
	@Autowired
	CartItemsRepo cartItemsRepo;
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	OrderDetailsRepo orderDetailsRepo;
	
	@Autowired
	OrderItemRepo orderItemRepo;
	
	
	public List<CartItemModel> getUserCartItems(Long uid,Pageable page) {
		List<CartItems> cartItemsList = cartItemsRepo.findByUserId(uid,page);
		List<CartItemModel> cartItemModels = new ArrayList<>();
		for(CartItems cartitems:cartItemsList) {
			CartItemModel cartItemModel = new CartItemModel();
			cartItemModel.setItemid(cartitems.getItemid());
			cartItemModel.setProductId(cartitems.getProductId());
			cartItemModel.setProductName(cartitems.getProductName());
			cartItemModel.setColor(cartitems.getColor());
			cartItemModel.setPrice(cartitems.getPrice());
			cartItemModel.setCategory(cartitems.getCategory());
			cartItemModel.setUserId(uid);
			cartItemModels.add(cartItemModel);
		}		
			return cartItemModels;
	}
	
	

	public String addtoCart(Long uid, Long pid) {
		User user = userRepo.getOne(uid);
		Cart cart = user.getCart();
		Product product = productRepo.getOne(pid);
		cart.getCartItems().add(product);
		product.getCarts().add(cart);		
		cartRepo.save(cart);
		productRepo.save(product);	
		
		CartItems cartItems = new CartItems();
		cartItems.setProductId(product.getProductid());
		cartItems.setProductName(product.getProductName());
		cartItems.setColor(product.getColor());
		cartItems.setPrice(product.getPrice());
		cartItems.setCategory(product.getCategory().getCategoryName());
		cartItems.setUserId(uid);
		
		cartItemsRepo.save(cartItems);
		return "Item Add";
	}

	public String removeItem(Long uid, Long pid) {
		User user = userRepo.getOne(uid);
		Cart cart = user.getCart();
		Product product = productRepo.getOne(pid);
		cart.getCartItems().remove(product);
		product.getCarts().remove(cart);		
		cartRepo.save(cart);
		productRepo.save(product);	
		
		CartItems cartItems = cartItemsRepo.findByUserIdAndProductId(uid,pid);
		cartItemsRepo.delete(cartItems);
		
		return "Item Removed";
	}



	public ProductModel searchUserCartItems(Long uid,Long pid) {
		ProductModel productModel = new ProductModel();
		User user = userRepo.getOne(uid);
		Cart cart = user.getCart();
		for(Product product:cart.getCartItems()) {
			if(product.getProductid().equals(pid)) {
				productModel.setProductId(product.getProductid());
				productModel.setProductName(product.getProductName());
				productModel.setColor(product.getColor());
				productModel.setPrice(product.getPrice());
				productModel.setCategory(product.getCategory().getCategoryName());
			}
		}
		return productModel;
	}



	public String submitItems(Long uid) {
		User user = userRepo.getOne(uid);
		Cart cart = user.getCart();
		Order existOrder = user.getOrder();
		List<Product> products = cart.getCartItems();
		List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
		
		for(Product product:products) {
			OrderDetails newOrderDetails = new OrderDetails();
			newOrderDetails.setProductName(product.getProductName());
			newOrderDetails.setColor(product.getColor());
			newOrderDetails.setPrice(product.getPrice());
			newOrderDetails.setProductCategory(product.getCategory().getCategoryName());	
			orderDetails.add(newOrderDetails);
		}
		orderDetailsRepo.saveAll(orderDetails);
		existOrder.setOrderDetails(orderDetails);		
		orderRepo.save(existOrder);
		
		Order order = user.getOrder();
		List<OrderItems> orderItemsList = new ArrayList<OrderItems>();
		for(Product product:products) {
			OrderItems orderItems = new OrderItems();
			for(OrderDetails orderDetail:order.getOrderDetails()) {
				if(orderDetail.getProductName().equalsIgnoreCase(product.getProductName())) {
					orderItems.setOrderId(orderDetail.getOrderid());
					orderItems.setProductName(product.getProductName());
					orderItems.setColor(product.getColor());
					orderItems.setPrice(product.getPrice());
					orderItems.setCategory(product.getCategory().getCategoryName());
					orderItems.setUserId(uid);
					orderItemsList.add(orderItems);
				}
			}
		}
		orderItemRepo.saveAll(orderItemsList);
		
		cart.getCartItems().removeAll(products);
		cartRepo.save(cart);	
		return "OrderAdded";
	}
}
