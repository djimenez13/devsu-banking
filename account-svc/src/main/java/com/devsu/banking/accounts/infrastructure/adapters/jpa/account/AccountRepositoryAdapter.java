package com.devsu.banking.accounts.infrastructure.adapters.jpa.account;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devsu.banking.accounts.domain.model.account.Account;
import com.devsu.banking.accounts.domain.model.account.gateway.AccountRepository;

@Component
public class AccountRepositoryAdapter implements AccountRepository {

	private AccountDataRepository repository;
	private ModelMapper modelMapper;
	

	public AccountRepositoryAdapter(AccountDataRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}


	@Override
	public List<Account> getAll() {
		
		List<Account> accounts = repository.findAll().stream().map(data -> {
			
			Account account = modelMapper.map(data, Account.class);
			return account;
		}).collect(Collectors.toList());
		
		return accounts;
	}
	
	@Override
	public List<Account> getAllByCustomer(String customerId) {
		
		List<Account> accounts = repository.findByCustomerId(customerId).stream().map(data -> {
			
			Account account = modelMapper.map(data, Account.class);
			return account;
		}).collect(Collectors.toList());
		
		return accounts;
	}
	
	@Override
	public Account get(String accountId) {

		Account account = modelMapper.map(repository.findById(accountId), Account.class);

		return account;
	}

	@Override
	public Account save(String customerId, Account account) {
		
		AccountData accountData = new AccountData(customerId, account.getNumber(), account.getType(), account.getBalance());

		Account accountUpdated = modelMapper.map(repository.save(accountData), Account.class);
		accountUpdated.setId(accountData.getId());

		return accountUpdated;
	}

	@Override
	public void delete(String accountId) {
		
		repository.deleteById(accountId);
		
	}

}
