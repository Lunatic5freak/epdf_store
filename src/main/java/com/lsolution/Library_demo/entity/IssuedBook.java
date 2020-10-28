package com.lsolution.Library_demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="issuedbook")
public class IssuedBook {
	
	@Id
	@Column(name="book_name")
	private String bookName;
	
	@Column(name="userid")
	private String username;
	
	public IssuedBook() {
		
	}

	public IssuedBook(String bookName, String username) {
		this.bookName = bookName;
		this.username = username;
	}


	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
