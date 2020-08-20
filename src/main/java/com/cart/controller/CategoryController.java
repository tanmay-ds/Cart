package com.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cart.model.CategoryModel;
import com.cart.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("categories/add")
	public List<CategoryModel> addCategories(@RequestBody List<CategoryModel> categoryModels) {
		return categoryService.addCategories(categoryModels);
	}
	
	@GetMapping("categories/get")
		public List<CategoryModel> getCategories(Pageable page) {
			return categoryService.getCategories(page);
		}
	
}
