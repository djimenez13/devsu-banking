package com.devsu.banking.common.exceptions.resources;

public class ResourceDuplicatedException extends RuntimeException {
	
	private static final long serialVersionUID = 7717621672659573155L;

	public ResourceDuplicatedException(String message) {
		super(message);
	}

}
