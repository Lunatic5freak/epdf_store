package com.lsolution.Library_demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsolution.Library_demo.entity.Users;

public interface UserRepo extends JpaRepository<Users,String> {

}
