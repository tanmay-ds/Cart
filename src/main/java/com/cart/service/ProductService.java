package com.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cart.dao.CategoryRepo;
import com.cart.dao.ProductRepo;
import com.cart.entity.Category;
import com.cart.entity.Product;
import com.cart.model.ProductModel;

@Service
public class ProductService {
	@Autowired
	ProductRepo productRepo;
	
	@Autowired 
	CategoryRepo categoryRepo;

	public List<ProductModel> getProducts(Pageable page) {
		Page<Product> products = productRepo.findAll(page);
		List<ProductModel> productModels = new ArrayList<ProductModel>();
		for(Product product:products) {
			ProductModel productModel = new ProductModel();
			productModel.setProductId(product.getProductid());
			productModel.setPrice(product.getPrice());
			productModel.setColor(product.getColor());
			productModel.setProductName(product.getProductName());
			productModel.setCategory(product.getCategory().getCategoryName());
			productModels.add(productModel);
		}
		return productModels;
	}

	public List<ProductModel> addProduct(List<ProductModel> productModels) {
		for(ProductModel productModel:productModels) {
			Product product = new Product();
			product.setProductName(productModel.getProductName());
			product.setColor(productModel.getColor());
			product.setPrice(productModel.getPrice());
			Category category = categoryRepo.findByCategoryName(productModel.getCategory());
			product.setCategory(category);
			productRepo.save(product);
			category.getProducts().add(product);
			categoryRepo.save(category);
		}
		return productModels;
	}

	public ProductModel updateProduct(Long id,Double price) {
		Product product = productRepo.getOne(id);
		ProductModel productModel = new ProductModel();
		product.setPrice(price);
		productRepo.save(product);
		
		productModel.setProductId(product.getProductid());
		productModel.setProductName(product.getProductName());
		productModel.setColor(product.getColor());
		productModel.setPrice(product.getPrice());
		Category category = categoryRepo.findByCategoryName(product.getCategory().getCategoryName());
		productModel.setCategory(category.getCategoryName());

		return productModel;
	}
	
	
	public ProductModel updateProduct(Long id,ProductModel productModel) {
		Product product = productRepo.getOne(id);
		product.setProductName(productModel.getProductName());
		product.setColor(productModel.getColor());
		product.setPrice(productModel.getPrice());
		Category category = categoryRepo.findByCategoryName(productModel.getCategory());
		product.setCategory(category);
		productRepo.save(product);
		return productModel;
	}
	
}
