package com.devsu.banking.accounts.infrastructure.adapters.jpa.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDataRepository extends JpaRepository<AccountData, String> {
	
	public List<AccountData> findByCustomerId(String customerId);

}
