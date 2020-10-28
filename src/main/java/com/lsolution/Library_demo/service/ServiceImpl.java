package com.lsolution.Library_demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsolution.Library_demo.dao.BookRepository;
import com.lsolution.Library_demo.entity.BookDetails;
@Service
public class ServiceImpl implements ServiceInterface{
	
	@Autowired
	private BookRepository dao;

	@Override
	@Transactional
	public List<BookDetails> showallBooks() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	@Transactional
	public BookDetails showById(int id) {
		Optional<BookDetails> theBook=dao.findById(id);
		if(theBook==null) {
			throw new RuntimeException("Book not found");
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public void saveBook(BookDetails theBook) {
		dao.save(theBook);
	}

	@Override
	@Transactional
	public void update(BookDetails theBook) {
		dao.save(theBook);
		
	}

}
