package com.app.party.genine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.party.genine.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Optional<Customer> findByEmailAndPassword(String email,String password);
	
	public Optional<Customer> findByEmail(String email);
}
