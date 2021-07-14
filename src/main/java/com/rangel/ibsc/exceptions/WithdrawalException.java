package com.rangel.ibsc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WithdrawalException extends RuntimeException{
	

	private static final long serialVersionUID = 3675627262492320752L;

	public WithdrawalException(String message) {
		super(message);
	}
	
	public WithdrawalException(String message, Throwable trowable) {
		super(message, trowable);
	}
}
