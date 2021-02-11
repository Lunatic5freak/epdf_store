package com.lsolution.Library_demo.service;

import java.util.List;
import java.util.Optional;

import com.lsolution.Library_demo.entity.Users;

public interface UserDetailsServiceInterface {

	public void saveUser(Users user);
	public Optional<Users> getUser(String Username);
}
