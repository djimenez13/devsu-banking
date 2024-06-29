package com.devsu.banking.accounts.infrastructure.adapters.jpa.movement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devsu.banking.accounts.domain.model.account.Account;
import com.devsu.banking.accounts.domain.model.movement.Movement;
import com.devsu.banking.accounts.domain.model.movement.gateway.MovementRepository;
import com.devsu.banking.accounts.infrastructure.adapters.jpa.account.AccountData;

@Component
public class MovementRepositoryAdapter implements MovementRepository {

	private MovementDataRepository repository;
	private ModelMapper modelMapper;

	public MovementRepositoryAdapter(MovementDataRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<Movement> getAllByAccount(String accountId) {

		List<Movement> movements = repository.findByAccountId(accountId).stream().map(data -> {

			Movement movement = modelMapper.map(data, Movement.class);
			return movement;
		}).collect(Collectors.toList());

		return movements;
	}

	@Override
	public Movement get(String movementId) {

		Movement movement = modelMapper.map(repository.findById(movementId), Movement.class);

		return movement;
	}

	@Override
	public Movement save(Account account, Movement movement) {

		AccountData accountData = modelMapper.map(account, AccountData.class);

		MovementData movementData = new MovementData(accountData, movement.getType(), movement.getAmount(),
				movement.getBalance());

		Movement movementUpdated = modelMapper.map(repository.save(movementData), Movement.class);
		movementUpdated.setId(movementData.getId());

		return movementUpdated;
	}

	@Override
	public void delete(String movementId) {
		
		repository.deleteById(movementId);
	}

}
