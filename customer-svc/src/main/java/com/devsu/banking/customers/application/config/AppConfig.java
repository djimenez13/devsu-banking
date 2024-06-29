package com.devsu.banking.customers.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devsu.banking.customers.domain.model.gateway.CustomerRepository;
import com.devsu.banking.customers.domain.usecase.CustomerUseCase;
import com.devsu.banking.customers.infrastructure.adapters.jpa.CustomerDataRepository;
import com.devsu.banking.customers.infrastructure.adapters.jpa.CustomerRepositoryAdapter;

@Configuration
@EnableAutoConfiguration
public class AppConfig {
	
	@Autowired
	private CustomerDataRepository customerDataRepository;
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public CustomerRepository customerRepository() {
		return new CustomerRepositoryAdapter(customerDataRepository, modelMapper());
	}
	
	@Bean
	public CustomerUseCase customerUseCase() {
		return new CustomerUseCase(customerRepository());
	}

}