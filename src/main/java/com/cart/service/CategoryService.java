package com.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cart.dao.CategoryRepo;
import com.cart.entity.Category;
import com.cart.entity.Product;
import com.cart.model.CategoryModel;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepo categoryRepo;

	public List<CategoryModel> addCategories(List<CategoryModel> categoryModels) {
		for(CategoryModel categoryModel:categoryModels) {
			Category category = new Category();
			category.setCategoryName(categoryModel.getCategoryName());
			categoryRepo.save(category);
			
		}
		return categoryModels;
	}

	public List<CategoryModel> getCategories(Pageable page) {
		Page<Category> categories = categoryRepo.findAll(page);
		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>();
		
		for(Category category:categories) {
				CategoryModel categoryModel = new CategoryModel();
				categoryModel.setCategoryid(category.getCatid());
				categoryModel.setCategoryName(category.getCategoryName());
				List<Long> productids = category.getProducts().stream().map(p->p.getProductid()).collect(Collectors.toList());
				categoryModel.setProductsId(productids);
				categoryModels.add(categoryModel);
			}
		return categoryModels;
	}

}
