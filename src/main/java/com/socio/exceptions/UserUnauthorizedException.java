package com.socio.exceptions;

public class UserUnauthorizedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserUnauthorizedException() {
	}
	public UserUnauthorizedException(String userId) {
		super("Un authorized access'" + userId + "'.");
	}
	
}
