package com.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cart.model.ProductModel;
import com.cart.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("product/getall")
	public List<ProductModel> getProducts(Pageable pageable){
		return productService.getProducts(pageable); 
	}
	
	@PostMapping("product/add")
	public List<ProductModel> addProduct(@RequestBody List<ProductModel> productModels) {
		return productService.addProduct(productModels);
	}
		
	@PutMapping("product/update/{id}/{price}")
	public ProductModel updateProduct(@PathVariable Long id,@PathVariable Double price) {
		return productService.updateProduct(id,price);
	}
	
	@PutMapping("product/update/{id}")
	public ProductModel updateProduct(@PathVariable Long id,@RequestBody ProductModel productModel) {
		return productService.updateProduct(id,productModel);
	}
}
