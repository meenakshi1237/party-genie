package com.app.party.genine.exceptions;

public class FeildValidationException extends RuntimeException{
	
	String message="Fields not validate properly!";

	public FeildValidationException() {
		
	}

	public FeildValidationException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
