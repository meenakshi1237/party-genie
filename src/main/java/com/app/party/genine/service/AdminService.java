package com.app.party.genine.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.party.genine.dao.AdminDao;
import com.app.party.genine.dto.AdminRequest;
import com.app.party.genine.dto.AdminResponse;
import com.app.party.genine.dto.ResponseStructure;
import com.app.party.genine.entity.Admin;
import com.app.party.genine.exceptions.UnauthorizedException;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<?> updadteAdmin(int id, AdminRequest adminRequest) {
		
		Optional<Admin> validAdmin=adminDao.findAdmin(id);
		
		if(validAdmin.isPresent()) {
					
			if(adminRequest.getName()!=null) {
				validAdmin.get().setName(adminRequest.getName());
			}
			if(adminRequest.getPhone()!=0) {
				validAdmin.get().setPhone(adminRequest.getPhone());
			}
			if(adminRequest.getEmail()!=null) {
				validAdmin.get().setEmail(adminRequest.getEmail());
			}
			if(adminRequest.getPassword()!=null) {
				validAdmin.get().setPassword(adminRequest.getPassword());
			}
			
			Admin updatedAdmin=adminDao.save(validAdmin);
			
			AdminResponse adminResponse=new AdminResponse();
			adminResponse.setEmail(updatedAdmin.getEmail());
			adminResponse.setName(updatedAdmin.getName());
			adminResponse.setPhone(updatedAdmin.getPhone());
			
			ResponseStructure<AdminResponse> response=new ResponseStructure<AdminResponse>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(adminResponse);
			response.setMessage("Updated Successfully");
			
			return new ResponseEntity<ResponseStructure<AdminResponse>>(response,HttpStatus.OK);
		}else {
			throw new UnauthorizedException();
		}
	}

	
}
