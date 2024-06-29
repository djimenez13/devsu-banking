package com.devsu.banking.accounts.infrastructure.entrypoints.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.banking.accounts.api.dto.GetAccountResponse;
import com.devsu.banking.accounts.domain.model.account.Account;
import com.devsu.banking.accounts.domain.usecase.account.AccountUseCase;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cuentas")
@AllArgsConstructor
public class AccountApiRest {

	private AccountUseCase useCase;


	@GetMapping
	public ResponseEntity<List<GetAccountResponse>> getAllAccounts() {

		List<GetAccountResponse> accounts = useCase.getAllAccounts().stream().map(account -> {

			return new GetAccountResponse(account.getNumber(), account.getType(), account.getBalance());
		}).collect(Collectors.toList());

		return ResponseEntity.ok(accounts);

	}

	@GetMapping("/cliente/{customerId}")
	public ResponseEntity<List<GetAccountResponse>> getAccountsByCustomer(@PathVariable String customerId) {

		List<GetAccountResponse> accounts = useCase.getAccountsByCustomer(customerId).stream().map(account -> {

			return new GetAccountResponse(account.getNumber(), account.getType(), account.getBalance());
		}).collect(Collectors.toList());

		return ResponseEntity.ok(accounts);

	}

	@GetMapping("/{accountId}")
	public ResponseEntity<GetAccountResponse> getById(@PathVariable String accountId) {

		Account account = useCase.getAccount(accountId);

		return ResponseEntity.ok(new GetAccountResponse(account.getNumber(), account.getType(),
				account.getBalance()));

	}

//	@PostMapping
//	public ResponseEntity<CreateAccountResponse> create(@RequestBody CreateAccountRequest request) {
//
//		// Just use case was implemented. This entry point was not
//		El registro de una nueva cuenta se sugiere implementar, utilizando transacciones saga orquestadas desde el servicio de clientes utilizando el siguiente endpoint:
//			POST /clientes/{customerId}/cuentas
//			Ver diseño de transacción saga
//		
//		return null;
//
//	}
//
//	@PutMapping("/{accountId}")
//	public ResponseEntity<UpdateAccountResponse> updateAccount(@PathVariable String accountId,
//			@RequestBody UpdateAccountRequest request) {
//
//		// Just use case was implemented. This entry point was not
//		La actualización de los datos de una cuenta no se implementa debido a que se sugiere que la información existente no sea modificada
//		
//		return null;
//
//	}

	@DeleteMapping("/{accountId}")
	public ResponseEntity<Void> deleteAccountById(@PathVariable String accountId) {

		useCase.deleteAccount(accountId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}