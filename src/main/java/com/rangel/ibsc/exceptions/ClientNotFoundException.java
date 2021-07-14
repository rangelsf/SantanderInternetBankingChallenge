package com.rangel.ibsc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 3442692056928809556L;


	public ClientNotFoundException(String message) {
		super(message);
	}
	
	public ClientNotFoundException(String message, Throwable trowable) {
		super(message, trowable);
	}
}
