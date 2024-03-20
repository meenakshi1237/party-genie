package com.app.party.genine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.party.genine.dto.ResponseStructure;

public class ApplicationExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> custommerNotFoundException(CustomerNotFoundException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setData("Not Found");
		responseStructure.setMessage("user not found");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(VenueNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> venueNotFoundException(VenueNotFoundException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setData("Not Found");
		responseStructure.setMessage("venue not found");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IdNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> catchIdNotPresentException(IdNotPresentException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData("Don't deal with null");
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_GATEWAY);

	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> bookingNotFoundException(BookingNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData("Don't deal with null");
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_GATEWAY);

	}

}
