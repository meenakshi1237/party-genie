package com.app.party.genine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.party.genine.dao.CustomerDao;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
}
