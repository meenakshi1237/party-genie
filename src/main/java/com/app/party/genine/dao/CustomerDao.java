package com.app.party.genine.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.party.genine.repository.CustomerRepository;

@Component
public class CustomerDao {
	
	@Autowired
	private CustomerRepository customerRepository;
}
