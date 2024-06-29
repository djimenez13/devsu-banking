package com.devsu.banking.customers.infrastructure.adapters.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devsu.banking.customers.domain.model.Customer;
import com.devsu.banking.customers.domain.model.gateway.CustomerRepository;

@Component
public class CustomerRepositoryAdapter implements CustomerRepository {

	private CustomerDataRepository repository;
	private ModelMapper modelMapper;

//	@Autowired
//	private SagaInstanceFactory sagaInstanceFactory;

//	@Autowired
//	private OpenAccountSaga openAccountSaga;

	public CustomerRepositoryAdapter(CustomerDataRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}
	

	@Override
	public List<Customer> getAll() {
		
		List<Customer> customers = repository.findAll().stream().map(data -> {
			
			Customer customer = modelMapper.map(data, Customer.class);
			return customer;
		}).collect(Collectors.toList());
		
		return customers;
	}

	@Override
	public Customer get(String customerId) {

		Customer customer = modelMapper.map(repository.findById(customerId), Customer.class);

		return customer;
	}

	@Override
	public Customer save(Customer customer) {

		CustomerData customerData = new CustomerData(customer.getName(), customer.getGender(), customer.getAge(),
				customer.getIdentification(), customer.getAddress(), customer.getPhoneNumber(), customer.getPassword());
		
		Customer customerUpdated = modelMapper.map(repository.save(customerData), Customer.class);
		customerUpdated.setCustomerId(customerData.getCustomerId());
		
		return customerUpdated;
	}

	@Override
	public void delete(String customerId) {

		repository.deleteById(customerId);

	}

//	@Override
//	public String requestOpenAccount(String customerId, int accountNumber, String accountType, double accountBalance) {
//		OpenAccountDetails details = new OpenAccountDetails(customerId, accountNumber, accountType, accountBalance);
//
//		OpenAccountSagaData sagaData = new OpenAccountSagaData(details);
//		sagaInstanceFactory.create(openAccountSaga, sagaData);
//
//		//TODO: Return the request id of the operation
//		return "1";
//	}

}
