package com.lsolution.Library_demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsolution.Library_demo.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

}
