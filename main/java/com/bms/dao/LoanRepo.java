package com.bms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.model.Loan;

public interface LoanRepo extends JpaRepository<Loan, Integer> {

}
