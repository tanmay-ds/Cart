package com.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cart.dao.CartRepo;
import com.cart.dao.UserRepo;
import com.cart.entity.Cart;
import com.cart.entity.Order;
import com.cart.entity.User;
import com.cart.model.UserModel;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	CartRepo cartRepo;

	public List<User> addUserOnly(List<UserModel> modelList) {
		List<User> users = new ArrayList<>();
		for(UserModel model:modelList) {
			User user = new User();
			Cart cart = new Cart();
			Order order = new Order();
			user.setUname(model.getUname());
			user.setCart(cart);
			user.setOrder(order);
			cartRepo.save(cart);
			userRepo.save(user);
			
		}
		return users;
	}

	public List<UserModel> getUsers(Pageable page) {
		Page<User> users = userRepo.findAll(page);
		List<UserModel> usermodels = new ArrayList<UserModel>();
		for (User user : users) {
			UserModel usermodel = new UserModel();
			usermodel.setUid(user.getUid());
			usermodel.setUname(user.getUname());
			usermodel.setCartid(user.getCart().getCid());
			usermodel.setUserOrderId(user.getOrder().getUserOrderid());
			usermodels.add(usermodel);
		}
		return usermodels;
	}
}
