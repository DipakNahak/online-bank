package com.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.model.CurrentTransaction;

public interface CurrentTransactionDao extends CrudRepository<CurrentTransaction, Long> {
	List<CurrentTransaction> findAll();
}
