package com.devsu.banking.common.exceptions.resources;

public class ResourceMissingDataException extends RuntimeException {

	private static final long serialVersionUID = -8263706655623188302L;

	public ResourceMissingDataException(String message) {
		super(message);
	}

}
