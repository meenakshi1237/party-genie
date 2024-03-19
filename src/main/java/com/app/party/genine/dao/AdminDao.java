package com.app.party.genine.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.party.genine.entity.Admin;
import com.app.party.genine.repository.AdminRepository;

@Component
public class AdminDao {

	@Autowired
	private AdminRepository adminRepository;

	public Optional<Admin> findAdmin(int id) {
		
		return adminRepository.findById(id);
	}
}
