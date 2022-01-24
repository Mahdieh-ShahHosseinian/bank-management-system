package com.bms.dto;

import java.util.Date;

public class AccountDTO {

	private int balance;

	private Date open_date;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Date getOpen_date() {
		return open_date;
	}

	public void setOpen_date(Date open_date) {
		this.open_date = open_date;
	}
}
