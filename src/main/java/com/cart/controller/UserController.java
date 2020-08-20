package com.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cart.model.UserModel;
import com.cart.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("user/get")
	public List<UserModel> getUsers(Pageable page) {
		return userService.getUsers(page);
	}
	
	@PostMapping("user/add")
	public ResponseEntity<?> addUserOnly(@RequestBody List<UserModel> modelList) {
		for(UserModel model:modelList) {	
			if(model.getUname()==null) {
				return ResponseEntity.badRequest().body("Name Cannot be null");
			}
		}
		return ResponseEntity.ok(userService.addUserOnly(modelList));
	}
}
