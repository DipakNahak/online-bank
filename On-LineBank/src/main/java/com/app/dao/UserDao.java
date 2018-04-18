package com.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.app.model.User;

public interface UserDao extends CrudRepository<User , Long> {
	
	User findByUsername(String username);
	
	User findByEmail(String email);
}
