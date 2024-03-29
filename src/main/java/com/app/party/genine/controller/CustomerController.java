package com.app.party.genine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.party.genine.dto.CustomerRequest;
import com.app.party.genine.dto.CustomerResponse;
import com.app.party.genine.dto.ResponseStructure;
import com.app.party.genine.entity.Customer;
import com.app.party.genine.entity.Venue;
import com.app.party.genine.exceptions.FeildValidationException;
import com.app.party.genine.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// this method for creating/registering the customer
	@Operation(description = "To Register Customer", summary = "customer will be registered")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "User Created")})
	@PostMapping(value="",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<CustomerResponse>> customerRegister(
			@Valid @RequestBody CustomerRequest customerRequest) {
		return customerService.customerRegister(customerRequest);
	}

	// this method for updating customer
	@Operation(description = "To Update Customer", summary = "customer details will be updated")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Details Updated"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content)})
	@PutMapping(value="/{customerId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<CustomerResponse>> updateCustomer(
			@Valid @RequestBody CustomerRequest customerRequest, @PathVariable int customerId, BindingResult result) {
		if (result.hasErrors()) {
			String errors = " ";
			for (FieldError error : result.getFieldErrors(errors)) {
				errors = error.getDefaultMessage() + ", ";
			}
			throw new FeildValidationException(errors);
		} else {
			return customerService.updateCustomer(customerRequest, customerId);
		}
	}

	// this method for deleting customer
	@Operation(description = "To Delete Customer", summary = "customer account will be deleted")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Account removed"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content)})
	@DeleteMapping(value="/{customerId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<String>> deleteCustomer(@PathVariable int customerId) {
		return customerService.deleteCustomer(customerId);
	}
	
	@Operation(description = "To get all the venues", summary = "Venue list will be displayed")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "venue list")})
	@GetMapping(value="/getall-venue",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenue(){
		return customerService.getAllVenue();
	}
	
	@Operation(description = "To Login user", summary = "User login")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "customer login")})
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<Customer>> login(@RequestParam String email,@RequestParam String password){
		return customerService.loginCustomer(email, password);
	}
	
	@Operation(description = "To get venue by location", summary = "venues at given location will be displayed")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "customer login")})
	@GetMapping(value="/venue/{location}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Venue>>> getVenuesBuLocation(@PathVariable String location){
		return customerService.getVenueByLocation(location);
	}
}
