package com.app.party.genine.exceptions;

public class BookingNotFoundException extends RuntimeException {
	private String message;

	public BookingNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
