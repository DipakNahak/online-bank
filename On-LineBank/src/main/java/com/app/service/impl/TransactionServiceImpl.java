package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CurrentAccountDao;
import com.app.dao.CurrentTransactionDao;
import com.app.dao.SavingAccountDao;
import com.app.dao.SavingTransactionDao;
import com.app.model.CurrentTransaction;
import com.app.model.SavingTransaction;
import com.app.model.User;
import com.app.service.TransactionService;
import com.app.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CurrentTransactionDao currentTransactionDao;
	
	@Autowired
	private SavingTransactionDao savingTransactionDao;
	
	@Autowired
	private CurrentAccountDao currentAccountDao;
	
	@Autowired
	private SavingAccountDao savingAccountDao;
	
	//@Autowired
	//private RecipientDao recipientDao;

	public List<CurrentTransaction> findCurrentTransactionList(String username){
        User user = userService.findByUsername(username);
        List<CurrentTransaction> currentTransactionList = user.getCurrentAccount().getCurrentTransactionList();

        return currentTransactionList;
    }

    public List<SavingTransaction> findSavingTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<SavingTransaction> savingsTransactionList = user.getSavingAccount().getSavingTransactionList();

        return savingsTransactionList;
    }
	
    public void saveCurrentDepositTransaction(CurrentTransaction currentTransaction) {
    	currentTransactionDao.save(currentTransaction);
    }

    public void saveSavingDepositTransaction(SavingTransaction savingTransaction) {
        savingTransactionDao.save(savingTransaction);
    }
    
    public void saveCurrentWithdrawTransaction(CurrentTransaction currentTransaction) {
    	currentTransactionDao.save(currentTransaction);
    }

    public void saveSavingWithdrawTransaction(SavingTransaction savingTransaction) {
        savingTransactionDao.save(savingTransaction);
    }


}
