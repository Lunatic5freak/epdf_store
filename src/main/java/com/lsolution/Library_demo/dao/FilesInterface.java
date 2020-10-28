package com.lsolution.Library_demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsolution.Library_demo.entity.File;

public interface FilesInterface extends JpaRepository<File,Integer> {

}
