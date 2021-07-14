package com.rangel.ibsc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionHistoryNotFoundException extends RuntimeException{
	

	private static final long serialVersionUID = -1027591554178961625L;

	public TransactionHistoryNotFoundException(String message) {
		super(message);
	}
	
	public TransactionHistoryNotFoundException(String message, Throwable trowable) {
		super(message, trowable);
	}
}
