package com.app.party.genine.exceptions;


public class InvalidVenueException extends RuntimeException {

	String exception="";

	public InvalidVenueException(String exception) {
		
		this.exception = exception;
	}

	public InvalidVenueException() {
		
	}
	
	@Override
	public String getMessage() {
		return exception;
	}
	
}
