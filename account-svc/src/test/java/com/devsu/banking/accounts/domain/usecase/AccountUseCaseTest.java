package com.devsu.banking.accounts.domain.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.devsu.banking.accounts.domain.model.account.Account;
import com.devsu.banking.accounts.domain.model.account.gateway.AccountRepository;
import com.devsu.banking.accounts.domain.mother.AccountMother;
import com.devsu.banking.accounts.domain.usecase.account.AccountUseCase;

@SpringBootTest()
public class AccountUseCaseTest {
	
	@Mock
	AccountRepository repository;
	
	@InjectMocks
	AccountUseCase useCase;

	@Test
	void getAll() {
		List<Account> expectedCustomers = AccountMother.oneElementList();
		int accountNumber = AccountMother.aValidObject().getNumber();
		when(repository.getAll()).thenReturn(expectedCustomers);

		List<Account> currentAccounts = useCase.getAllAccounts();

		assertNotNull(currentAccounts);
		assertEquals(1, currentAccounts.size());
		assertEquals(accountNumber, currentAccounts.get(0).getNumber());
	}

}
