package com.devsu.banking.accounts.domain.usecase.movement;

import java.util.List;

import com.devsu.banking.accounts.domain.model.account.Account;
import com.devsu.banking.accounts.domain.model.account.gateway.AccountRepository;
import com.devsu.banking.accounts.domain.model.exceptions.InsufficientFundsException;
import com.devsu.banking.accounts.domain.model.movement.Movement;
import com.devsu.banking.accounts.domain.model.movement.gateway.MovementRepository;
import com.devsu.banking.common.exceptions.resources.ResourceNotFoundException;

public class MovementUseCase {
	
	private MovementRepository repository;
	private AccountRepository accountRepository;
	

	public MovementUseCase(MovementRepository repository) {
		this.repository = repository;
	}
	

	public List<Movement> getMovementsByAccount(String accountId) {
		
		List<Movement> movements = repository.getAllByAccount(accountId);
		
		if (movements.isEmpty()) {
			throw new ResourceNotFoundException("Movements not found for the account");
		}
		
		return movements;
		
	}

	public Movement getMovement(String movementId) {
		
		Movement movementFound = repository.get(movementId);
		
		if (movementFound == null) {
			throw new ResourceNotFoundException("Movement not found");
		}
		
		return movementFound;
	}

	public Movement createMovement(String customerId, String accountId, Movement movement) {
		
		Account account = accountRepository.get(accountId);
		
		if (account == null) {
			throw new ResourceNotFoundException("Account not found on trying to create a movement");
		}
		
		synchronized (this) {
			
			// Movement is a withdraw
			if (movement.getAmount() <= 0) {
				
				// Insufficient funds
				if (account.getBalance() - Math.abs(movement.getAmount()) < 0) {
					throw new InsufficientFundsException("Movement cannot be done because of insufficient funds");
					
				} else {
					// Withdraw can be done
					account.setBalance(account.getBalance() - Math.abs(movement.getAmount()));
					accountRepository.save(customerId, account);
					
				}
			}

			return repository.save(account, movement);
		}
	}
	
	public void deleteMovement(String movementId) {
		Movement movementFound = repository.get(movementId);
		
		if (movementFound == null) {
			throw new ResourceNotFoundException("Movement not found on trying to delete it");
		}
		repository.delete(movementId);
	}

}
