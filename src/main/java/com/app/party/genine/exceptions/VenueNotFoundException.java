package com.app.party.genine.exceptions;

public class VenueNotFoundException extends RuntimeException{
	private String message;
	
	public VenueNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}	
}
