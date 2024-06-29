package com.devsu.banking.customers.domain.model.gateway;

import java.util.List;

import com.devsu.banking.customers.domain.model.Customer;

public interface CustomerRepository {

	List<Customer> getAll();

	Customer get(String customerId);

	Customer save(Customer customer);

	void delete(String customerId);
	
	String requestOpenAccount(String customerId, int accountNumber, String accountType, double accountBalance);

}
