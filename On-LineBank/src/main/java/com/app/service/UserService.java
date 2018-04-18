package com.app.service;

import com.app.model.User;

public interface UserService {

	User findByUsername(String username);
	
	User findByEmail(String email);
	boolean checkUserExists(String username,String email);
	
	boolean checkUsernameExists(String username);
	
	boolean checkEmailExists(String email);
	
	void save(User user);
}