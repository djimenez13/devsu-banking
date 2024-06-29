package com.devsu.banking.customers.infrastructure.adapters.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDataRepository extends JpaRepository<CustomerData, String> {

}
