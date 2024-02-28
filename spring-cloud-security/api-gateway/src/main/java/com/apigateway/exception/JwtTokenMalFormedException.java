package com.apigateway.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMalFormedException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JwtTokenMalFormedException(String message) {
		super(message);
	}

}
