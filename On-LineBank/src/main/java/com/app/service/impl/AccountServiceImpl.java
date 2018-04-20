package com.app.service.impl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CurrentAccountDao;
import com.app.dao.SavingAccountDao;
import com.app.model.CurrentAccount;
import com.app.model.CurrentTransaction;
import com.app.model.SavingAccount;
import com.app.model.SavingTransaction;
import com.app.model.User;
import com.app.service.AccountService;
import com.app.service.TransactionService;
import com.app.service.UserService;

@Service
public class AccountServiceImpl implements AccountService {

	private static int nextAccountNumber=11223145;
	
	@Autowired
	private CurrentAccountDao	currentAccountdao;
	 
	@Autowired
	private SavingAccountDao savingAccountdao; 
	
	@Autowired
    private CurrentAccountDao currentAccountDao;

    @Autowired
    private SavingAccountDao savingAccountDao;

    @Autowired
    private UserService userService;
    
    @Autowired
    private TransactionService transactionService;
	
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
	 public void deposit(String accountType, double amount, Principal principal) {
	        User user = userService.findByUsername(principal.getName());

	        if (accountType.equalsIgnoreCase("Current")) {
	            CurrentAccount currentAccount = user.getCurrentAccount();
	            currentAccount.setAccountBalance(currentAccount.getAccountBalance().add(new BigDecimal(amount)));
	            currentAccountDao.save(currentAccount);

	            Date date = new Date();

	            CurrentTransaction currentTransaction = new CurrentTransaction(date, "Deposit to Current Account", "Account", "Finished", amount, currentAccount.getAccountBalance(), currentAccount);
	            transactionService.saveCurrentDepositTransaction(currentTransaction);
	            
	        } else if (accountType.equalsIgnoreCase("Saving")) {
	            SavingAccount savingAccount = user.getSavingAccount();
	            savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(amount)));
	            savingAccountDao.save(savingAccount);

	            Date date = new Date();
	            SavingTransaction savingTransaction = new SavingTransaction(date, "Deposit to saving Account", "Account", "Finished", amount, savingAccount.getAccountBalance(), savingAccount);
	            transactionService.saveSavingDepositTransaction(savingTransaction);
	        }
	    }
	    
	    public void withdraw(String accountType, double amount, Principal principal) {
	        User user = userService.findByUsername(principal.getName());

	        if (accountType.equalsIgnoreCase("Current")) {
	        	CurrentAccount currentAccount = user.getCurrentAccount();
	        	currentAccount.setAccountBalance(currentAccount.getAccountBalance().subtract(new BigDecimal(amount)));
	        	currentAccountDao.save(currentAccount);

	            Date date = new Date();

	            CurrentTransaction currentTransaction = new CurrentTransaction(date, "Withdraw from Current Account", "Account", "Finished", amount, currentAccount.getAccountBalance(), currentAccount);
	            transactionService.saveCurrentWithdrawTransaction(currentTransaction);
	        } else if (accountType.equalsIgnoreCase("Saving")) {
	            SavingAccount savingAccount = user.getSavingAccount();
	            savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
	            savingAccountDao.save(savingAccount);

	            Date date = new Date();
	            SavingTransaction savingTransaction = new SavingTransaction(date, "Withdraw from saving Account", "Account", "Finished", amount, savingAccount.getAccountBalance(), savingAccount);
	            transactionService.saveSavingWithdrawTransaction(savingTransaction);
	        }
	    }
	
	private int accountGen(){
		
		return ++nextAccountNumber;
	}

}
