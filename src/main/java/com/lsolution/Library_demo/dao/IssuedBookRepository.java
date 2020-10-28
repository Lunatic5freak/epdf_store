package com.lsolution.Library_demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsolution.Library_demo.entity.IssuedBook;

public interface IssuedBookRepository extends JpaRepository<IssuedBook, String> {

}
