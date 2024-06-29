package com.devsu.banking.customers.infrastructure.saga;

import com.devsu.banking.customers.infrastructure.adapters.jpa.saga.OpenAccountDetails;
import com.devsu.banking.customers.infrastructure.adapters.jpa.saga.OpenAccountRejectionReason;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OpenAccountSagaData {
	
	private String requestId;
	private OpenAccountDetails details;
	private OpenAccountRejectionReason rejectionReason;
	
	
	public OpenAccountSagaData(OpenAccountDetails details) {
		this.details = details;
	}

}
