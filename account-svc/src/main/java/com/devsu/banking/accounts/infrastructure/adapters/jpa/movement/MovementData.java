package com.devsu.banking.accounts.infrastructure.adapters.jpa.movement;

import java.time.LocalDateTime;
import java.util.UUID;

import com.devsu.banking.accounts.infrastructure.adapters.jpa.account.AccountData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movements")
public class MovementData {

	@Id
	private String id;
	private LocalDateTime time;
	private String type;
	private double amount;
	private double balance;
	private String description;
	private boolean status;

	@OneToOne
	@JoinColumn(name = "account", nullable = false)
	private AccountData account;

	public MovementData(AccountData account, String type, double amount, double balance) {

		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.account = account;
	}

	@PrePersist
	public void prePersist() {
		this.id = UUID.randomUUID().toString();
		this.time = LocalDateTime.now();
		this.status = true;
		
		if (getAmount() >= 0) {
			
			this.description = String.format("Deposit of {}", getAmount());
			
		} else {
			
			this.description = String.format("Withdraw of {}", Math.abs(getAmount()));
			
		}
	}

}
