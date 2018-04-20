package com.app.service;

import java.security.Principal;

import com.app.model.CurrentAccount;
import com.app.model.CurrentTransaction;
import com.app.model.SavingAccount;
import com.app.model.SavingTransaction;

public interface AccountService {

	CurrentAccount createCurrentAccount();
	SavingAccount createSavingAccount();
	void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);
     
}
