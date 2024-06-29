package com.devsu.banking.customers.infrastructure.entrypoint.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.banking.customers.api.dto.CreateCustomerRequest;
import com.devsu.banking.customers.api.dto.CreateCustomerResponse;
import com.devsu.banking.customers.api.dto.GetCustomerResponse;
import com.devsu.banking.customers.api.dto.UpdateCustomerRequest;
import com.devsu.banking.customers.api.dto.UpdateCustomerResponse;
import com.devsu.banking.customers.domain.model.Customer;
import com.devsu.banking.customers.domain.usecase.CustomerUseCase;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class CustomerApiRest {

	private CustomerUseCase useCase;

	@GetMapping
	public ResponseEntity<List<GetCustomerResponse>> getAllCustomers() {

		List<GetCustomerResponse> customers = useCase.getAllCustomers().stream().map(customer -> {
			
			return new GetCustomerResponse(customer.getName(), customer.getGender(),
					customer.getAge(), customer.getIdentification(), customer.getAddress(), customer.getPhoneNumber());
		}).collect(Collectors.toList());

		return ResponseEntity.ok(customers);

	}

	@GetMapping("/{customerId}")
	public ResponseEntity<GetCustomerResponse> getCustomerById(@PathVariable String customerId) {

		Customer customer = useCase.getCustomer(customerId);

		return ResponseEntity.ok(new GetCustomerResponse(customer.getName(), customer.getGender(),
				customer.getAge(), customer.getIdentification(), customer.getAddress(), customer.getPhoneNumber()));

	}

	@PostMapping
	public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerRequest request) {

		Customer customer = useCase.createCustomer(
				new Customer(request.getName(), request.getGender(), request.getAge(), request.getIdentification(),
						request.getAddress(), request.getPhoneNumber(), request.getPassword()));

		return new ResponseEntity<>(new CreateCustomerResponse(customer.getCustomerId(), customer.getName(),
				customer.getGender(), customer.getAge(), customer.getIdentification(), customer.getAddress(),
				customer.getPhoneNumber()), HttpStatus.CREATED);

	}

	@PutMapping("/{customerId}")
	public ResponseEntity<UpdateCustomerResponse> updateCustomer(@PathVariable String customerId,
			@RequestBody UpdateCustomerRequest request) {

		Customer customer = useCase.updateCustomer(customerId, new Customer(request.getName(), request.getGender(),
				request.getAge(), request.getIdentification(), request.getAddress(), request.getPhoneNumber()));

		return ResponseEntity.ok(new UpdateCustomerResponse(customer.getCustomerId(), customer.getName(),
				customer.getGender(), customer.getAge(), customer.getIdentification(), customer.getAddress(),
				customer.getPhoneNumber()));

	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomerById(@PathVariable String customerId) {

		useCase.deleteCustomer(customerId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

//	@PostMapping("/{customerId}/cuentas")
//	public ResponseEntity<OpenAccountResponse> requestOpenAccount(@PathVariable String customerId,
//			@RequestBody OpenAccountRequest request) {
//
//		String requestId = useCase.requestOpenAccount(customerId, request.getAccountNumber(), request.getAccountType(),
//				request.getAccountBalance());
//
//		return new ResponseEntity<>(new OpenAccountResponse(requestId), HttpStatus.CREATED);
//
//	}

}
