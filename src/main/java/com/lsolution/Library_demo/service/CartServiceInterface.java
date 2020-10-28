package com.lsolution.Library_demo.service;

import java.util.List;
import java.util.Optional;

import com.lsolution.Library_demo.entity.CartItems;

public interface CartServiceInterface {
	
	public List<CartItems> showallItems();
	public Optional<CartItems> showbyuserid(int userid);
	public void removeitem(int productid);
	public void addoCart(CartItems item);
//	public void update_quantity(int productid);
	

}
