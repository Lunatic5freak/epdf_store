package com.lsolution.Library_demo.service;

import java.util.List;
import java.util.Optional;

import com.lsolution.Library_demo.dao.ProjectRepo;
import com.lsolution.Library_demo.entity.Projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepo prj;

    @Transactional
    public List<Projects> allProjects(){
        return prj.findAll();
    }

    @Transactional
    public List<Projects> findByname(String keyword){
        return prj.findByName(keyword);
    }
    
    @Transactional
    public void saveProject(Projects project) {
    	prj.save(project);
    }
    
    @Transactional
    public void deleteProject(int id) {
    	prj.deleteById(id);
    }

    @Transactional
    public Optional<Projects> findbyId(int id){
        return prj.findById(id);
    }
}