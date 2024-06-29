package com.devsu.banking.common.exceptions.resources;

public class ResourceInvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 7717621672659573155L;

	public ResourceInvalidDataException(String message) {
		super(message);
	}

}
