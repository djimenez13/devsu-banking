package com.devsu.banking.customers.application.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devsu.banking.common.exceptions.model.Response;
import com.devsu.banking.common.exceptions.resources.ResourceDuplicatedException;
import com.devsu.banking.common.exceptions.resources.ResourceInvalidDataException;
import com.devsu.banking.common.exceptions.resources.ResourceMissingDataException;
import com.devsu.banking.common.exceptions.resources.ResourceNotFoundException;
import com.devsu.banking.common.exceptions.resources.ResourceOperationNotAllowedException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ ResourceNotFoundException.class })
	protected ResponseEntity<Response> handleNotFoundConflict(RuntimeException exception, WebRequest request) {
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(
						Response.builder()
						.message(exception.getMessage())
						.build());
	}
	
	@ExceptionHandler({
		ResourceInvalidDataException.class, 
		ResourceMissingDataException.class,
		ResourceOperationNotAllowedException.class,
		ResourceDuplicatedException.class,
		ResourceMissingDataException.class
		})
	protected ResponseEntity<Response> handleBadRequestConflict(RuntimeException exception, WebRequest request) {
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(
						Response.builder()
							.message(exception.getMessage())
							.build());
	}
	
	@ExceptionHandler({ RuntimeException.class })
	protected ResponseEntity<Response> handleUnexpectedConflict(RuntimeException exception, WebRequest request) {
		exception.printStackTrace();
		
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(
						Response.builder()
							.message("Unexpected error")
							.build());
	}
	
	

}
