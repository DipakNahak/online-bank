package com.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.app.model.SavingAccount;

public interface SavingAccountDao extends CrudRepository<SavingAccount, Long> {
	
	SavingAccount findByAccountNumber(int accountNumber);

}
