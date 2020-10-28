package com.lsolution.Library_demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsolution.Library_demo.dao.BookRepository;
import com.lsolution.Library_demo.entity.BookDetails;

@Service
public class BookServiceImpl implements BookServiceInterface {
	
	@Autowired
	private BookRepository dao;

	@Override
	public void savebook(BookDetails theBook) {
		
		
	}

	@Override
	public void deletebook(int id) {
		
	}

	@Override
	public void updateBook(int id) {
		
	}

	@Override
	public List<BookDetails> findAllBook() {
		return null;
	}

	@Override
	public Optional<BookDetails> findById(int id) {
		return null;
	}
	
	@Override
	@Transactional
	public BookDetails search(String name) {
		BookDetails b;
		String query="select * from book_details where=:name";
		List<BookDetails> sh;
		return null;
	}
	
	
	

}
