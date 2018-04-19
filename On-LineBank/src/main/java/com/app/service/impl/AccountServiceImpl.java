package com.app.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CurrentAccountDao;
import com.app.dao.SavingAccountDao;
import com.app.model.CurrentAccount;
import com.app.model.SavingAccount;
import com.app.service.AccountService;
import com.app.service.UserService;

@Service
public class AccountServiceImpl implements AccountService {

	private static int nextAccountNumber=11223145;
	
	@Autowired
	private CurrentAccountDao	currentAccountdao;
	
	@Autowired
	private SavingAccountDao savingAccountdao;
	
	@Autowired
	private UserService uerService;
	
	@Override
	public CurrentAccount createCurrentAccount() {
		CurrentAccount currentAccount=new CurrentAccount();
		currentAccount.setAccountBalance(new BigDecimal(0.0));
		currentAccount.setAccountNumber(accountGen());
		
		currentAccountdao.save(currentAccount);
		
		return currentAccountdao.findByAccountNumber(currentAccount.getAccountNumber());
	}

	@Override
	public SavingAccount createSavingAccount() {
		SavingAccount savingAccount=new SavingAccount();
		savingAccount.setAccountBalance(new BigDecimal(0.0));
		savingAccount.setAccountNumber(accountGen());
		
		savingAccountdao.save(savingAccount);
		
		return savingAccountdao.findByAccountNumber(savingAccount.getAccountNumber());
	}
	
	private int accountGen(){
		
		return ++nextAccountNumber;
	}

}
