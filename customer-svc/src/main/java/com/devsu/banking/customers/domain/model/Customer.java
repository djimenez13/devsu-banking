package com.devsu.banking.customers.domain.model;

public class Customer extends Person {

	private String customerId;
	private String password;
	private boolean status;

	public Customer() {
	}

	public Customer(String name, String gender, int age, String identification, String address,
			String phoneNumber) {
		
		super(name, gender, age, identification, address, phoneNumber);
		this.status = true;
	}

	public Customer(String name, String gender, int age, String identification, String address,
			String phoneNumber, String password) {
		
		super(name, gender, age, identification, address, phoneNumber);
		this.password = password;
		this.status = true;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
