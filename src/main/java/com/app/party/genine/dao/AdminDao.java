package com.app.party.genine.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.app.party.genine.entity.Admin;
import com.app.party.genine.repository.AdminRepository;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepository adminRepository;

	public Optional<Admin> findAdmin(int id) {
		
		return adminRepository.findById(id);
	}

	public Admin save(Optional<Admin> validAdmin) {
		return adminRepository.save(validAdmin.get());
	}
}
