package com.devsu.banking.customers.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerResponse {
	
	private String customerId;
	private String name;
	private String gender;
	private int age;
	private String identification;
	private String address;
	private String phoneNumber;

}
