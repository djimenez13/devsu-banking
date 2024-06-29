package com.devsu.banking.accounts.domain.usecase.account;

import java.util.List;

import com.devsu.banking.accounts.domain.model.account.Account;
import com.devsu.banking.accounts.domain.model.account.gateway.AccountRepository;
import com.devsu.banking.common.exceptions.resources.ResourceNotFoundException;

public class AccountUseCase {

	private AccountRepository repository;
	

	public AccountUseCase(AccountRepository repository) {
		this.repository = repository;
	}
	

	public List<Account> getAllAccounts() {
		
		return repository.getAll();
		
	}

	public List<Account> getAccountsByCustomer(String customerId) {
		
		List<Account> accounts = repository.getAllByCustomer(customerId);
		
		if (accounts.isEmpty()) {
			throw new ResourceNotFoundException("Accounts not found for the customer");
		}
		
		return accounts;
		
	}

	public Account getAccount(String accountId) {
		
		Account accountFound = repository.get(accountId);
		
		if (accountFound == null) {
			throw new ResourceNotFoundException("Account not found");
		}
		
		return accountFound;
	}

	public Account createAccount(String customerId, Account account) {
		
		return repository.save(customerId, account);
	}

	public Account updateAccount(String customerId, String accountId, Account account) {
		
		Account accountFound = repository.get(accountId);
		
		if (accountFound == null) {
			throw new ResourceNotFoundException("Account not found on trying to update its information");
		}
		
		account.setId(accountFound.getId());

		return repository.save(customerId, account);
	}
	
	public void deleteAccount(String accountId) {
		
		Account accountFound = repository.get(accountId);
		
		if (accountFound == null) {
			throw new ResourceNotFoundException("Account not found on trying to delete it");
		}
		repository.delete(accountId);
	}

}
