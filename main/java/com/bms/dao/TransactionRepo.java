package com.bms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
