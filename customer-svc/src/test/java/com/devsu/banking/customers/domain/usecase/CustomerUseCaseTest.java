package com.devsu.banking.customers.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.devsu.banking.customers.domain.model.Customer;
import com.devsu.banking.customers.domain.model.gateway.CustomerRepository;
import com.devsu.banking.customers.domain.mother.CustomerMother;

@SpringBootTest()
public class CustomerUseCaseTest {
	
	@Mock
	CustomerRepository repository;
	
	@InjectMocks
	CustomerUseCase useCase;

	@Test
	void getAll() {
		List<Customer> expectedCustomers = CustomerMother.oneElementList();
		String customerName = CustomerMother.aValidObject().getName();
		when(repository.getAll()).thenReturn(expectedCustomers);

		List<Customer> currentCustomers = useCase.getAllCustomers();

		assertNotNull(currentCustomers);
		assertEquals(1, currentCustomers.size());
		assertEquals(customerName, currentCustomers.get(0).getName());
	}

}
