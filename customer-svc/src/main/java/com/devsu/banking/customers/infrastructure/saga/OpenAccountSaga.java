package com.devsu.banking.customers.infrastructure.saga;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devsu.banking.accounts.api.messaging.commands.OpenAccountCommand;
import com.devsu.banking.accounts.api.messaging.replies.InvalidAmount;
import com.devsu.banking.accounts.api.messaging.replies.TooManyAccounts;
import com.devsu.banking.customers.infrastructure.adapters.jpa.CustomerData;
import com.devsu.banking.customers.infrastructure.adapters.jpa.CustomerDataRepository;
import com.devsu.banking.customers.infrastructure.adapters.jpa.saga.OpenAccountRejectionReason;
import com.devsu.banking.customers.infrastructure.adapters.jpa.saga.OpenAccountRequest;
import com.devsu.banking.customers.infrastructure.adapters.jpa.saga.OpenAccountRequestRepository;

import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

@Component
public class OpenAccountSaga implements SimpleSaga<OpenAccountSagaData> {
	
	@Autowired
	private OpenAccountRequestRepository repository;
	
	@Autowired
	private CustomerDataRepository customerRepository;
	
	private SagaDefinition<OpenAccountSagaData> sagaDefinition = 
			step()
				.invokeLocal(this::createRequest)
				.withCompensation(this::rejectRequest)
			.step()
				.invokeLocal(this::checkCustomerExists)
			.step()
				.invokeParticipant(this::openAccount)
				.onReply(InvalidAmount.class, this::handleInvalidAmount)
				.onReply(TooManyAccounts.class, this::handleTooManyAccounts)
			.step()
				.invokeLocal(this::aproveRequest)
			.build();

	
	@Override
	public SagaDefinition<OpenAccountSagaData> getSagaDefinition() {
		return sagaDefinition;
	}
	
	private void createRequest(OpenAccountSagaData sagaData) {
		OpenAccountRequest request = new OpenAccountRequest(sagaData.getDetails());
		
		repository.save(request);
		
		sagaData.setRequestId(request.getId());

		System.out.println("request created!");
	}
	
	private void rejectRequest(OpenAccountSagaData sagaData) {
		OpenAccountRequest openAccountRequest = repository.findById(sagaData.getRequestId()).get();
		
		openAccountRequest.reject(sagaData.getRejectionReason());
		
		repository.save(openAccountRequest);

		System.out.println("request rejected: " + sagaData.getRejectionReason());
	}

	private void checkCustomerExists(OpenAccountSagaData sagaData) {
		Optional<CustomerData> maybeCustomer = customerRepository.findById(sagaData.getDetails().getCustomerId());
		
		if (maybeCustomer.isEmpty()) {
			sagaData.setRejectionReason(OpenAccountRejectionReason.UNKNOWN_CUSTOMER);
			System.out.println("Unknown customer: " + sagaData.getDetails().getCustomerId());
		}
	}
	
	private CommandWithDestination openAccount(OpenAccountSagaData sagaData) {
		String customerId = sagaData.getDetails().getCustomerId();
		int accountNumber = sagaData.getDetails().getAccountNumber();
		String accountType = sagaData.getDetails().getAccountType();
		double accountBalance = sagaData.getDetails().getAccountBalance();

		return send(new OpenAccountCommand(customerId, accountNumber, accountType, accountBalance))
				.to("customer-events-channel")
				.build();
	}
	
	private void handleInvalidAmount(OpenAccountSagaData sagaData, InvalidAmount reply) {
		sagaData.setRejectionReason(OpenAccountRejectionReason.INVALID_AMOUNT);

		System.out.println("handle invalid amount done!");
	}
	
	private void handleTooManyAccounts(OpenAccountSagaData sagaData, TooManyAccounts reply) {
		sagaData.setRejectionReason(OpenAccountRejectionReason.TOO_MANY_ACCOUNTS);

		System.out.println("handle too many accounts done!");
	}
	
	private void aproveRequest(OpenAccountSagaData sagaData) {

		repository.findById(sagaData.getRequestId()).get().approve();

		System.out.println("request approved!");
	}

}
