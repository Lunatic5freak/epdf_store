package com.lsolution.Library_demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsolution.Library_demo.entity.QusAns;

public interface QuestionRepo extends JpaRepository<QusAns, Integer> {

}
