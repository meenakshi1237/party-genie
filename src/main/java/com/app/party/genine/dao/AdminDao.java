package com.app.party.genine.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.app.party.genine.entity.Admin;
import com.app.party.genine.entity.FarmHouse;
import com.app.party.genine.entity.PartyHall;
import com.app.party.genine.entity.Venue;
import com.app.party.genine.entity.WeddingHall;
import com.app.party.genine.exceptions.InvalidVenueException;
import com.app.party.genine.exceptions.UnauthorizedException;
import com.app.party.genine.repository.AdminRepository;

@Component
public class AdminDao {

	@Autowired
	private AdminRepository adminRepository;

	public Optional<Admin> findAdmin(int id) {
		
		return adminRepository.findById(id);
	}
	
	
}
