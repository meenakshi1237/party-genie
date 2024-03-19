package com.app.party.genine.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.party.genine.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private ResponseStructure<String> responseStructure;
	
	@ExceptionHandler(InvalidVenueException.class)
	public ResponseEntity<ResponseStructure<String>> invalidExceptionHandler(InvalidVenueException exception){
	
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		responseStructure.setData("Invalid venue type is not allowed to save");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ResponseStructure<String>> unauthorizedExceptionHandler(UnauthorizedException exception){
	
		responseStructure.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		responseStructure.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		responseStructure.setData("User is not Authorized to save the venue");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.UNAUTHORIZED);
	}

}
