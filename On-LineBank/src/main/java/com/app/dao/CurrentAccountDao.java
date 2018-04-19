package com.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.app.model.CurrentAccount;

public interface CurrentAccountDao extends CrudRepository<CurrentAccount , Long> {
	CurrentAccount findByAccountNumber(int accountNumber);
	
}
