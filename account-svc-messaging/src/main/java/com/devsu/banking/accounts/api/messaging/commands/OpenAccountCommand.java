package com.devsu.banking.accounts.api.messaging.commands;

import io.eventuate.tram.commands.common.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountCommand implements Command {
	
	private String customerId;
	private int accountNumber;
	private String accountType;
	private double accountBalance;

}
