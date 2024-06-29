package com.devsu.banking.customers.domain.mother;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devsu.banking.customers.domain.model.Customer;

public class CustomerMother {

	public static Customer aValidObject() {
		Customer customer = new Customer("David Jimenez", "M", 12, "12345678", "Alguna direccion", "123456789");

		return customer;
	}

	public static Customer aNullObject() {
		return null;
	}

	public static Optional<Customer> aValidOptional() {
		return Optional.of(aValidObject());
	}

	public static Optional<Customer> anEmptyOptional() {
		return Optional.empty();
	}

	public static List<Customer> oneElementList() {
		return new ArrayList<Customer>() {
			private static final long serialVersionUID = -2290854254599613037L;

			{
				add(CustomerMother.aValidObject());
			}
		};
	}

	public static List<Customer> anEmptyList() {
		return new ArrayList<Customer>();
	}

}
