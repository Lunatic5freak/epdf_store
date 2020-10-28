package com.lsolution.Library_demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsolution.Library_demo.dao.CartItemsRepo;
import com.lsolution.Library_demo.entity.CartItems;

@Service
public class Cartimpl implements CartServiceInterface{

	@Autowired
	private CartItemsRepo cartrepo;
	
	@Override
	@Transactional
	public List<CartItems> showallItems() {
		return cartrepo.findAll();
	}

	@Override
	@Transactional
	public Optional<CartItems> showbyuserid(int userid) {
		//int id=Integer.parseInt(userid);
		return cartrepo.findById(userid);
	}

	@Override
	@Transactional
	public void removeitem(int productid) {
		cartrepo.deleteById(productid);
	}

	@Override
	@Transactional
	public void addoCart(CartItems item) {
		cartrepo.save(item);
		
	}

}
