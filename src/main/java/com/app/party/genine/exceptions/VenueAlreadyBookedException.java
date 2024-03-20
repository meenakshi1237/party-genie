package com.app.party.genine.exceptions;

public class VenueAlreadyBookedException extends RuntimeException {
	private String message;

	public VenueAlreadyBookedException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
