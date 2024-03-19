package com.app.party.genine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.party.genine.dto.CustomerRequest;
import com.app.party.genine.dto.CustomerResponse;
import com.app.party.genine.dto.ResponseStructure;
import com.app.party.genine.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	public ResponseEntity<ResponseStructure<CustomerResponse>> customerRegister(@Valid @RequestBody CustomerRequest customerRequest){
		return null;
	}
}
