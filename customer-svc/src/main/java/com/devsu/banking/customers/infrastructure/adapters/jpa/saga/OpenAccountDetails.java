package com.devsu.banking.customers.infrastructure.adapters.jpa.saga;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OpenAccountDetails {
	
	private String customerId;
	int accountNumber;
	String accountType;
	double accountBalance;

}
