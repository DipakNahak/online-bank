package com.app.service;

import java.util.List;

import com.app.model.CurrentTransaction;
import com.app.model.SavingTransaction;

public interface TransactionService {

	List<CurrentTransaction> findCurrentTransactionList(String username);
	List<SavingTransaction> findSavingTransactionList(String username);
	
	void saveCurrentDepositTransaction(CurrentTransaction currentTransaction);
	void saveSavingDepositTransaction(SavingTransaction savingTransaction);
	
	void saveCurrentWithdrawTransaction(CurrentTransaction currentTransaction); 

    void saveSavingWithdrawTransaction(SavingTransaction savingTransaction);
}
