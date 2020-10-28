package com.lsolution.Library_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsolution.Library_demo.dao.RoleRepository;
import com.lsolution.Library_demo.entity.RoleEntity;
import com.lsolution.Library_demo.entity.Users;

@Service
public class UserDetailsService implements UserDetailsServiceInterface {
	
	@Autowired
	private RoleRepository role;

	@Override
	public void saveUser(Users user) {
		String s=user.getPassword();
		s= "{noop}"+s;
		user.setPassword(s);
		RoleEntity roles=new RoleEntity();
		roles.setUser(user);
		role.save(roles);
	}

}
