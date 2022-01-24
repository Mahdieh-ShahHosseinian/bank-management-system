package com.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.dao.TransactionRepo;
import com.bms.model.Transaction;

@Service
public class TransactionService implements ServiceInterface<Transaction> {

	@Autowired
	private TransactionRepo repo;

	@Override
	public List<Transaction> getAll() {
		return repo.findAll();
	}

	@Override
	public Transaction add(Transaction transaction) {
		return repo.save(transaction);
	}

	@Override
	public Transaction get(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Transaction update(Transaction transaction, int id) {
		Transaction updatedTransaction = get(id);
		updatedTransaction.setAmount(transaction.getAmount());
		return add(updatedTransaction);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}
}