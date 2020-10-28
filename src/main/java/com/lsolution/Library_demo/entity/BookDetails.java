package com.lsolution.Library_demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="book_details")
public class BookDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="author")
	private String author;
	
	@Column(name="type")
	private String type;
	
	@Column(name="status")
	private String Status;
	
	@Column(name="price")
	private int price;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fileid")
	private File file;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="imageid")
	private Book_Images image;
	
	public BookDetails() {
		
	}

	public BookDetails(String name, String author, String type, String status, int price) {
		this.name = name;
		this.author = author;
		this.type = type;
		Status = status;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Book_Images getImage() {
		return image;
	}

	public void setImage(Book_Images image) {
		this.image = image;
	}

}
