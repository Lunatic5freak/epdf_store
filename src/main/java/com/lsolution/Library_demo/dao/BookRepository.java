package com.lsolution.Library_demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lsolution.Library_demo.entity.BookDetails;

public interface BookRepository extends JpaRepository<BookDetails, Integer> {

	@Query(value="select * from book_details e where e.name like %:keyword%", nativeQuery=true)
	List<BookDetails> findByName(@Param(value = "keyword") String keyword);

}
