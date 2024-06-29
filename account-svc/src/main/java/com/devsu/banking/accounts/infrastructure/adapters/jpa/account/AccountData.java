package com.devsu.banking.accounts.infrastructure.adapters.jpa.account;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountData {
	
	@Id
	private String id;
	
	private String customerId;
	private int number;
	private String type;
	private double balance;
	private boolean status;
	

	public AccountData(String customerId, int number, String type, double balance) {
		this.customerId = customerId;
		this.number = number;
		this.type = type;
		this.balance = balance;
	}
	
	@PrePersist
	public void prePersist() {
		this.id = UUID.randomUUID().toString();
		this.status = true;
	}

}
