package com.app.party.genine.exceptions;

public class UnauthorizedException extends RuntimeException {

	String exception="";

	public UnauthorizedException(String exception) {
		
		this.exception = exception;
	}

	public UnauthorizedException() {
		
	}
	
	@Override
	public String getMessage() {
		return exception;
	}
	
}
