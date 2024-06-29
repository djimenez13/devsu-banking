package com.devsu.banking.accounts.domain.model.account.gateway;

import java.util.List;

import com.devsu.banking.accounts.domain.model.account.Account;

public interface AccountRepository {

	List<Account> getAll();

	List<Account> getAllByCustomer(String customerId);

	Account get(String accountId);

	Account save(String customerId, Account account);

	void delete(String accountId);

}
