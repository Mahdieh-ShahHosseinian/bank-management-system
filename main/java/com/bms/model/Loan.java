package com.bms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Loan {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loan_id;

	@Column
	private int interest;

	@Column
	private int duration;

	@Column
	private int total_amount;

	@Column
	private int remaining_amount;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	// setters and getters
	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}

	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public int getRemaining_amount() {
		return remaining_amount;
	}

	public void setRemaining_amount(int remaining_amount) {
		this.remaining_amount = remaining_amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
