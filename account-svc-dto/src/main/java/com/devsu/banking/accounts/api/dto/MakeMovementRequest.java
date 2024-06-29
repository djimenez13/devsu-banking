package com.devsu.banking.accounts.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MakeMovementRequest {
	
	private String customerId;
	private String accountId;
	private String type;
	private double amount;
	private double balance;
}
