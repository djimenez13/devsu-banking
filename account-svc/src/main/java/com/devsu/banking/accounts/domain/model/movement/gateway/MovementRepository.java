package com.devsu.banking.accounts.domain.model.movement.gateway;

import java.util.List;

import com.devsu.banking.accounts.domain.model.account.Account;
import com.devsu.banking.accounts.domain.model.movement.Movement;

public interface MovementRepository {

	List<Movement> getAllByAccount(String acountId);

	Movement get(String movementId);

	Movement save(Account account, Movement movement);

	void delete(String movementId);

}
