package com.devsu.banking.accounts.domain.model.account;

public class Account {

	private String id;
	private int number;
	private String type;
	private double balance;
	private boolean status;

	public Account() {}

	public Account(int number, String type, double balance) {
		this.number = number;
		this.type = type;
		this.balance = balance;
		this.status = true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
