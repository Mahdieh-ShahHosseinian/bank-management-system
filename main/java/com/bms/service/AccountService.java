package com.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.dao.AccountRepo;
import com.bms.model.Account;

@Service
public class AccountService implements ServiceInterface<Account> {

	@Autowired
	private AccountRepo repo;

	@Override
	public List<Account> getAll() {
		return repo.findAll();
	}

	@Override
	public Account add(Account account) {
		return repo.save(account);
	}

	@Override
	public Account get(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Account update(Account account, int id) {

		Account updatedAccount = get(id);
		updatedAccount.setBalance(account.getBalance());
		updatedAccount.setLoans(account.getLoans());
		updatedAccount.setTransactions(account.getTransactions());
		return add(updatedAccount);
	}

	public Account updateBalance(int balance, int id) {

		Account updatedAccount = get(id);
		updatedAccount.setBalance(balance);
		return add(updatedAccount);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}
}