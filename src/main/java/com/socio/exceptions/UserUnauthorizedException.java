package com.socio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserUnauthorizedException() {
	}
	public UserUnauthorizedException(String userId) {
		super("User is unauthorized to access" + userId + "'.");
	}	
}
