package com.devsu.banking.accounts.domain.mother;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devsu.banking.accounts.domain.model.account.Account;

public class AccountMother {

	public static Account aValidObject() {
		Account account = new Account( 123456789, "A", 99000.0);

		return account;
	}

	public static Account aNullObject() {
		return null;
	}

	public static Optional<Account> aValidOptional() {
		return Optional.of(aValidObject());
	}

	public static Optional<Account> anEmptyOptional() {
		return Optional.empty();
	}

	public static List<Account> oneElementList() {
		return new ArrayList<Account>() {
			
			private static final long serialVersionUID = -6915989114033975104L;

			{
				add(AccountMother.aValidObject());
			}
		};
	}

	public static List<Account> anEmptyList() {
		return new ArrayList<Account>();
	}

}
