package com.app.party.genine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.party.genine.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
