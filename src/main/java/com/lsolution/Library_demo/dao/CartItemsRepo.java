package com.lsolution.Library_demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsolution.Library_demo.entity.CartItems;

public interface CartItemsRepo extends JpaRepository<CartItems, Integer> {

}
