package com.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.app.model.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {
	Role findByName(String name);
}
