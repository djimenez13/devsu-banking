package com.devsu.banking.accounts.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MakeMovementResponse {
	
	private String customerName;
	private String accountName;
	private String type;
	private double amount;
	private double balance;
	private String description;

}
