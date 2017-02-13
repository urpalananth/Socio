package com.socio.exceptions;

public class UserNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserNotFoundException() {
	}
	public UserNotFoundException(String userId) {
		super("could not find user '" + userId + "'.");
	}
	
}
