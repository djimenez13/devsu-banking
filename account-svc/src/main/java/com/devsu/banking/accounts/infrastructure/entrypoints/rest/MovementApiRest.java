package com.devsu.banking.accounts.infrastructure.entrypoints.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.banking.accounts.api.dto.GetMovementResponse;
import com.devsu.banking.accounts.domain.model.movement.Movement;
import com.devsu.banking.accounts.domain.usecase.movement.MovementUseCase;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/movimientos")
@AllArgsConstructor
public class MovementApiRest {

	private MovementUseCase useCase;

	@GetMapping("/cuenta/{accountId}")
	public ResponseEntity<List<GetMovementResponse>> getMovementsByAccount(@PathVariable String accountId) {

		List<GetMovementResponse> movements = useCase.getMovementsByAccount(accountId).stream().map(movement -> {

			return new GetMovementResponse(movement.getAccount().getNumber(), movement.getType(), movement.getAmount(),
					movement.getBalance());
		}).collect(Collectors.toList());

		return ResponseEntity.ok(movements);

	}

	@GetMapping("/{movementId}")
	public ResponseEntity<GetMovementResponse> getById(@PathVariable String movementId) {

		Movement movement = useCase.getMovement(movementId);

		return ResponseEntity.ok(new GetMovementResponse(movement.getAccount().getNumber(), movement.getType(),
				movement.getAmount(), movement.getBalance()));

	}

//	@PostMapping
//	public ResponseEntity<CreateMovementResponse> createMovement(@RequestBody CreateMovementRequest request) {
//
//		// Just use case was implemented. This entry point was not
//		El registro de un nuevo movimiento se sugiere implementar utilizando transacciones saga orquestadas desde el servicio de cuentas utilizando el siguiente endpoint:
//			POST /movimientos/cuenta/{accountId}
//			Ver diseño de transacción saga
//		
//		return null;
//
//	}
//
//	@PutMapping("/{accountId}")
//	public ResponseEntity<UpdateMovementResponse> updateMovement(@PathVariable String accountId,
//			@RequestBody UpdateMovementRequest request) {
//
//		// Not implemented
//		La actualización de los datos de un movimiento no se implementa debido a que se sugiere que la información existente no sea modificada 
//		
//		return null;
//
//	}

	@DeleteMapping("/{movementId}")
	public ResponseEntity<Void> deleteAccountById(@PathVariable String movementId) {

		useCase.deleteMovement(movementId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}