package com.lsolution.Library_demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart_item")
public class CartItems {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="oredr_id")
	private int orderid;
	
	@Column(name="userid")
	private String userid;
	
	@Column(name="productid")
	private int productid;
	
	@Column(name="price")
	private int price;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="order_date")
	private LocalDate order_date;
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDate getOrder_date() {
		return order_date;
	}
	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}
	
	public CartItems() {
		
	}
	public CartItems(String userid, int productid, int price, int quantity,
			LocalDate order_date) {
		this.userid = userid;
		this.productid = productid;
		this.price = price;
		this.quantity = quantity;
		this.order_date = order_date;
	}

}
