package com.devsu.banking.customers.domain.usecase;

import java.util.List;

import com.devsu.banking.common.exceptions.resources.ResourceNotFoundException;
import com.devsu.banking.customers.domain.model.Customer;
import com.devsu.banking.customers.domain.model.gateway.CustomerRepository;

public class CustomerUseCase {

	private CustomerRepository repository;
	

	public CustomerUseCase(CustomerRepository repository) {
		this.repository = repository;
	}
	

	public List<Customer> getAllCustomers() {
		
		return repository.getAll();
		
	}
	

	public Customer getCustomer(String customerId) {
		
		Customer customerFound = repository.get(customerId);
		
		if (customerFound == null) {
			throw new ResourceNotFoundException("Customer not found");
		}
		
		return customerFound;
	}

	public Customer createCustomer(Customer customer) {

		return repository.save(customer);
	}

	public Customer updateCustomer(String customerId, Customer customer) {
		
		Customer customerFound = repository.get(customerId);
		
		if (customerFound == null) {
			throw new ResourceNotFoundException("Customer not found on trying to update its information");
		}
		
		customer.setCustomerId(customerFound.getCustomerId());
		customer.setPassword(customerFound.getPassword());

		return repository.save(customer);
	}

	public void deleteCustomer(String customerId) {
		
		Customer customerFound = repository.get(customerId);
		
		if (customerFound == null) {
			throw new ResourceNotFoundException("Customer not found on trying to delete it");
		}
		
		repository.delete(customerId);
	}

//	public String requestOpenAccount(String customerId, int accountNumber, String accountType, double accountBalance) {
//
//		String requestId = repository.requestOpenAccount(customerId, accountNumber, accountType, accountBalance);
//
//		return requestId;
//	}

}
