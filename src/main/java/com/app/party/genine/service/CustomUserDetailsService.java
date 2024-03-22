package com.app.party.genine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.party.genine.entity.Admin;
import com.app.party.genine.entity.Customer;
import com.app.party.genine.repository.AdminRepository;
import com.app.party.genine.repository.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByEmail(username).orElse(null);
		
		if(admin==null) {
			
			Customer customer=customerRepository.findByEmail(username).orElse(null);
			return customer;
			
		}
		
		return admin;
	}
	
}
