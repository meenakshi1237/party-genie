package com.app.party.genine.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.springframework.stereotype.Repository;


import com.app.party.genine.entity.Customer;
import com.app.party.genine.repository.CustomerRepository;



@Repository

public class CustomerDao {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer getCustomerById(int id) {
		return customerRepository.findById(id).orElse(null);
	}
	
	public boolean removeCustomer(int id) {
		Customer customer = getCustomerById(id);
		
		if(customer != null) {
			customerRepository.delete(customer);
			return true;
		}
		return false;
	}
}
