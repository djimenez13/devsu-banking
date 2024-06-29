package com.devsu.banking.common.exceptions.resources;

public class ResourceOperationNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = -4280762455252347255L;

	public ResourceOperationNotAllowedException(String message) {
		super(message);
	}

}
