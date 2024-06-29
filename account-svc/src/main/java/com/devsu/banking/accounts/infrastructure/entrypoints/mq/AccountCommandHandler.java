package com.devsu.banking.accounts.infrastructure.entrypoints.mq;

import org.springframework.beans.factory.annotation.Autowired;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

import com.devsu.banking.accounts.api.messaging.commands.OpenAccountCommand;
import com.devsu.banking.accounts.api.messaging.replies.InvalidAmount;
import com.devsu.banking.accounts.api.messaging.replies.OpenAccountDone;
import com.devsu.banking.accounts.api.messaging.replies.TooManyAccounts;
import com.devsu.banking.accounts.domain.model.account.Account;
import com.devsu.banking.accounts.domain.model.exceptions.InvalidAmountException;
import com.devsu.banking.accounts.domain.model.exceptions.TooManyAccountsException;
import com.devsu.banking.accounts.domain.usecase.account.AccountUseCase;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

public class AccountCommandHandler {

	@Autowired
	private AccountUseCase useCase;

	
	public CommandHandlers commandHandlerDefinitions() {
		return SagaCommandHandlersBuilder.fromChannel("customer-events-channel")
				.onMessage(OpenAccountCommand.class, this::openAccount)
				.build();
	}
	
	public Message openAccount(CommandMessage<OpenAccountCommand> commandMessage) {
		
		OpenAccountCommand cmd = commandMessage.getCommand();
		
		try {
			
			Account account = new Account(cmd.getAccountNumber(), cmd.getAccountType(), cmd.getAccountBalance());
			
			useCase.createAccount(cmd.getCustomerId(), account);
			
			return withSuccess(new OpenAccountDone());
			
		} catch (InvalidAmountException e) {
			e.printStackTrace();
			return withFailure(new InvalidAmount());
			
		} catch (TooManyAccountsException e) {
			e.printStackTrace();
			return withFailure(new TooManyAccounts());
		}
		
		
	}

}
