package com.lsolution.Library_demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsolution.Library_demo.dao.RoleRepository;
import com.lsolution.Library_demo.dao.UserRepo;
import com.lsolution.Library_demo.entity.RoleEntity;
import com.lsolution.Library_demo.entity.Users;

@Service
public class UserDetailsService implements UserDetailsServiceInterface {
	
	@Autowired
	private RoleRepository role;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public void saveUser(Users user) {
		String s=user.getPassword();
		s= "{noop}"+s;
		user.setPassword(s);
		user.setEnabled(1);
		RoleEntity roles=new RoleEntity();
		roles.setAuthority("USER");
		roles.setUser(user);
		role.save(roles);
	}

	@Override
	public Optional<Users> getUser(String Username) {
		return userRepo.findById(Username);
	}

}
