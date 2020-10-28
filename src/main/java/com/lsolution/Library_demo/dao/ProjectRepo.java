package com.lsolution.Library_demo.dao;

import java.util.List;

import com.lsolution.Library_demo.entity.Projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepo extends JpaRepository<Projects,Integer> {
    @Query(value="select * from projects e where e.name like %:keyword%", nativeQuery=true)
	List<Projects> findByName(@Param(value = "keyword") String keyword);
}