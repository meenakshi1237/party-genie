package com.app.party.genine.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.party.genine.repository.CustomerRepository;
@Repository
public class CustomerDao {
	
	@Autowired
	private CustomerRepository customerRepository;
}
