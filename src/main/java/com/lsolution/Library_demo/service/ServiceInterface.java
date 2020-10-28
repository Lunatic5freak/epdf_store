package com.lsolution.Library_demo.service;

import java.util.List;

import com.lsolution.Library_demo.entity.BookDetails;

public interface ServiceInterface {
	
	public List<BookDetails> showallBooks();
	public BookDetails showById(int id);
	public void deleteById(int id);
	public void update(BookDetails theBook);
	public void saveBook(BookDetails theBook);

}
