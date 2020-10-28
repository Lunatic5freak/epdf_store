package com.lsolution.Library_demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="requestedbook")
public class RequestedBook {

	@Id
	@Column(name="book_name")
	private String book_name;
	
	@Column(name="userid")
	private String userid;

	public RequestedBook() {
		
	}
	
	public RequestedBook(String book_name, String userid) {
		this.book_name = book_name;
		this.userid = userid;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	
}
