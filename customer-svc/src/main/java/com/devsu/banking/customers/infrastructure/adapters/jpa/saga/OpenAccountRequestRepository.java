package com.devsu.banking.customers.infrastructure.adapters.jpa.saga;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenAccountRequestRepository extends JpaRepository<OpenAccountRequest, String> {

}
