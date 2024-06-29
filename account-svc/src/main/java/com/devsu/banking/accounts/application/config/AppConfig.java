package com.devsu.banking.accounts.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devsu.banking.accounts.domain.model.account.gateway.AccountRepository;
import com.devsu.banking.accounts.domain.model.movement.gateway.MovementRepository;
import com.devsu.banking.accounts.domain.usecase.account.AccountUseCase;
import com.devsu.banking.accounts.domain.usecase.movement.MovementUseCase;
import com.devsu.banking.accounts.infrastructure.adapters.jpa.account.AccountDataRepository;
import com.devsu.banking.accounts.infrastructure.adapters.jpa.account.AccountRepositoryAdapter;
import com.devsu.banking.accounts.infrastructure.adapters.jpa.movement.MovementDataRepository;
import com.devsu.banking.accounts.infrastructure.adapters.jpa.movement.MovementRepositoryAdapter;
import com.devsu.banking.accounts.infrastructure.entrypoints.mq.AccountCommandHandler;

import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;

@Configuration
@EnableAutoConfiguration
public class AppConfig {
	
	@Autowired
	private AccountDataRepository accountDataRepository;
	
	@Autowired
	private MovementDataRepository movementDataRepository;
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public AccountRepository accountRepository() {
		return new AccountRepositoryAdapter(accountDataRepository, modelMapper());
	}
	
	@Bean
	public MovementRepository movementRepository() {
		return new MovementRepositoryAdapter(movementDataRepository, modelMapper());
	}
	
	@Bean
	public AccountUseCase accountUseCase() {
		return new AccountUseCase(accountRepository());
	}
	
	@Bean
	public MovementUseCase movementUseCase() {
		return new MovementUseCase(movementRepository());
	}
	
	@Bean
	public AccountCommandHandler accountCommandHandler() {
		System.out.println("account handler defined!");
		return new AccountCommandHandler();
	}
	
	@Bean
	public CommandDispatcher consumerCommandDispatcher(AccountCommandHandler target, 
			SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
		
		return sagaCommandDispatcherFactory.make("accountCommandDispatcher", 
				target.commandHandlerDefinitions());
	}

}