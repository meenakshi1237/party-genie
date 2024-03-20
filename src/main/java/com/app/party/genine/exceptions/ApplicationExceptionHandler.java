package com.app.party.genine.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.party.genine.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
		
	@ExceptionHandler(InvalidVenueException.class)
	public ResponseEntity<ResponseStructure<String>> invalidExceptionHandler(InvalidVenueException exception){
	
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		responseStructure.setData("Invalid venue type is not allowed to save");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ResponseStructure<String>> unauthorizedExceptionHandler(UnauthorizedException exception){
		
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		responseStructure.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		responseStructure.setData("User is not Authorized to save the venue");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(IdNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> catchIdNotPresentException(IdNotPresentException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData("Don't deal with null");
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(FeildValidationException.class)
	public ResponseEntity<ResponseStructure<String>> catchFeildValidationException(FeildValidationException exception){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(exception.getMessage());
		rs.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
}
