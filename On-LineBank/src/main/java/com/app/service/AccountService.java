package com.app.service;

import com.app.model.CurrentAccount;
import com.app.model.SavingAccount;

public interface AccountService {

	CurrentAccount createCurrentAccount();
	SavingAccount createSavingAccount();
}
