package com.devsu.banking.accounts.domain.model.movement;

import java.time.LocalDateTime;

import com.devsu.banking.accounts.domain.model.account.Account;

public class Movement {
	
	private String id;
	private LocalDateTime time;
	private String type;
	private double amount;
	private double balance;
	private String description;
	private boolean status;
	
	private Account account;
	
	public Movement() {}

	public Movement(Account account, String type, double amount, double balance) {
		this.account = account;
		this.type = type;
		this.amount = amount;
		this.status = true;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
