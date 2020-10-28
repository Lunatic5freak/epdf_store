package com.lsolution.Library_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsolution.Library_demo.dao.QuestionRepo;
import com.lsolution.Library_demo.entity.QusAns;

@Service
public class QustionService {
	
	@Autowired
	private QuestionRepo qus;
	
	@Transactional
	public void postQuestion(QusAns qust) {
		qus.save(qust);
	}
	
	@Transactional
	public void deleteQus(QusAns qust) {
		qus.delete(qust);
	}
	
	@Transactional
	public List<QusAns> showqusans() {
		return qus.findAll();
	}
	
}
