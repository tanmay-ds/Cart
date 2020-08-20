package com.cart.model;

import java.util.ArrayList;
import java.util.List;


public class CategoryModel {
	
	private Long categoryid;
	private String categoryName;
	private List<Long> productsId = new ArrayList<Long>();
	
	
	public Long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Long> getProductsId() {
		return productsId;
	}
	public void setProductsId(List<Long> products) {
		this.productsId = products;
	}
}
