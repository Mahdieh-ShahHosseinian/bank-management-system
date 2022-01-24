package com.bms.dto;

public class LoanDTO {

	private int interest;

	private int duration;

	private int total_amount;

	private int remaining_amount;

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
}
