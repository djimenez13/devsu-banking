package com.devsu.banking.accounts.infrastructure.adapters.jpa.movement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementDataRepository extends JpaRepository<MovementData, String> {
	
	public List<MovementData> findByAccountId(String accountId);

}
