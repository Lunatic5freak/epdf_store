package com.lsolution.Library_demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsolution.Library_demo.entity.RequestedBook;

public interface RequestedBookRepo extends JpaRepository<RequestedBook, String> {

}
