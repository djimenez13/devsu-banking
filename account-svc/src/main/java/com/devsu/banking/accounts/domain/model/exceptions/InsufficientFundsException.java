package com.devsu.banking.accounts.domain.model.exceptions;

public class InsufficientFundsException extends RuntimeException {

	private static final long serialVersionUID = -7106048486469731151L;

	public InsufficientFundsException(String message) {
		super(message);
	}

}
