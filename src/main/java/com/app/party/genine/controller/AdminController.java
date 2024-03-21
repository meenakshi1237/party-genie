package com.app.party.genine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.party.genine.dto.AdminRequest;
import com.app.party.genine.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Operation(description = "To Update the Admin Details", summary = "Admin Details will be updated")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateAdmin(@PathVariable int id,@RequestBody AdminRequest adminRequest){
		return adminService.updadteAdmin(id,adminRequest);
	}

}
