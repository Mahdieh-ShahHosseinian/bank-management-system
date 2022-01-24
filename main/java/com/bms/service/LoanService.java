package com.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.dao.LoanRepo;
import com.bms.model.Loan;

@Service
public class LoanService implements ServiceInterface<Loan> {

	@Autowired
	private LoanRepo repo;

	@Override
	public List<Loan> getAll() {
		return repo.findAll();
	}

	@Override
	public Loan add(Loan loan) {
		return repo.save(loan);
	}

	@Override
	public Loan get(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Loan update(Loan loan, int id) {
		Loan updatedLoan = get(id);
		updatedLoan.setDuration(loan.getDuration());
		updatedLoan.setRemaining_amount(loan.getRemaining_amount());
		updatedLoan.setInterest(loan.getInterest());
		return add(updatedLoan);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

	public Loan updateRamainingAmount(int remainingAmount, int id) {
		Loan updatedLoan = get(id);
		updatedLoan.setRemaining_amount(remainingAmount);
		return add(updatedLoan);
	}
}
