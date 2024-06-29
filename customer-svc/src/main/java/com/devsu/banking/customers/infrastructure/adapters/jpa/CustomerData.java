package com.devsu.banking.customers.infrastructure.adapters.jpa;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class CustomerData {
	
	@Id
	private String customerId;
	private String password;
	private boolean status;
	
	private String name;
	private String gender;
	private int age;
	private String identification;
	private String address;
	private String phoneNumber;
	
	
	public CustomerData(String name, String gender, int age,
			String identification, String address, String phoneNumber, String password) {
		
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.identification = identification;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	@PrePersist
	public void prePersist() {
		this.customerId = UUID.randomUUID().toString();
		this.status = true;
		//TODO: Cifrar password
	}
	
}
