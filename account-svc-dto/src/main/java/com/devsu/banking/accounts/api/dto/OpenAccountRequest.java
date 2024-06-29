package com.devsu.banking.accounts.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountRequest {
	
	private int accountNumber;
	private String accountType;
	private double accountBalance;
}
