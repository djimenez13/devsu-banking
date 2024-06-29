package com.devsu.banking.common.exceptions.resources;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7684204912560167954L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
