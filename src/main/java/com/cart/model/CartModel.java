package com.cart.model;

import java.util.ArrayList;
import java.util.List;

public class CartModel {
	private Long cid;
	private List<ProductModel> itemsId = new ArrayList<ProductModel>();
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public List<ProductModel> getItemsId() {
		return itemsId;
	}
	public void setItemsId(List<ProductModel> items) {
		this.itemsId = items;
	}
}