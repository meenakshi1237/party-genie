package com.app.party.genine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.party.genine.dto.CustomerRequest;
import com.app.party.genine.dto.CustomerResponse;
import com.app.party.genine.dto.ResponseStructure;
import com.app.party.genine.exceptions.FeildValidationException;
import com.app.party.genine.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// this method for creating/registering the customer
	@PostMapping()
	public ResponseEntity<ResponseStructure<CustomerResponse>> customerRegister(
			@Valid @RequestBody CustomerRequest customerRequest) {
		return customerService.customerRegister(customerRequest);
	}
	
	//this method for updating customer
	@PutMapping("/{customerId}")
	public ResponseEntity<ResponseStructure<CustomerResponse>> updateCustomer(
			@Valid @RequestBody CustomerRequest customerRequest,@PathVariable int customerId, BindingResult result) {
		if(result.hasErrors()) {
			String errors=" ";
			for(ObjectError error:result.getAllErrors()) {
				errors= error.getDefaultMessage()+", ";
			}
			throw new FeildValidationException(errors);
		}else {
		return customerService.updateCustomer(customerRequest, customerId);
		}
	}
	
	//this method for deleting customer
	@DeleteMapping("/{customerId}")
	public ResponseEntity<ResponseStructure<String>> deleteCustomer(@PathVariable int customerId){
		return customerService.deleteCustomer(customerId);
	}
	
	
}
