package com.app.party.genine.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.party.genine.repository.CustomerRepository;

public class CustomerDao {
	
	@Autowired
	private CustomerRepository customerRepository;
}
