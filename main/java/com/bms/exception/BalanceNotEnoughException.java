package com.bms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
public class BalanceNotEnoughException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BalanceNotEnoughException() {
		super("Balance is not enough");
	}
}