package com.socio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidParamException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidParamException() {
	}
	public InvalidParamException(String userId) {
		super("Un authorized access'" + userId + "'.");
	}
	
}
