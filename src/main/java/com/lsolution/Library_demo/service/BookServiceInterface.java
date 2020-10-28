package com.lsolution.Library_demo.service;

import java.util.List;
import java.util.Optional;

import com.lsolution.Library_demo.entity.BookDetails;

public interface BookServiceInterface {
	
	public void savebook(BookDetails theBook);
	public void deletebook(int id);
	public void updateBook(int id);
	public List<BookDetails> findAllBook();
	public Optional<BookDetails> findById(int id);
	public BookDetails search(String name);

}
