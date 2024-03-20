package com.app.party.genine.exceptions;

public class IdNotPresentException extends RuntimeException{
	
	String message="Id Not Present Exception";

	public IdNotPresentException() {
		
	}

	public IdNotPresentException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	
}
