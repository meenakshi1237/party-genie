package com.app.party.genine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.party.genine.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	
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
		rs.setData("Please check your request");
		rs.setMessage(exception.getMessage());
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
}
