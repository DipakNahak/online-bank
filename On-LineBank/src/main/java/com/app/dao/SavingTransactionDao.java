package com.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.model.SavingTransaction;

public interface SavingTransactionDao extends CrudRepository<SavingTransaction, Long> {
 List<SavingTransaction> findAll();
}
