package com.app.party.genine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.party.genine.dto.VenueRequest;
import com.app.party.genine.service.VenueService;
import com.app.party.genine.util.BookingStatus;

@RestController
@RequestMapping("/admin/venues")
public class VenueController {
	
	@Autowired
	private VenueService venueService;
	
	@PostMapping("/{id}/{venueType}")
	public ResponseEntity<?> saveVenue(@PathVariable String venueType,@RequestBody VenueRequest venueRequest,@PathVariable int id){
		return venueService.saveVenue(venueType,venueRequest,id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findAllVenues(@PathVariable int id){
		return venueService.findAllVenues(id);
	}
	
	@PatchMapping("/{adminId}/{venueId}/{status}")
	public ResponseEntity<?> updateVenueStatus(@PathVariable int adminId,@PathVariable int venueId,@RequestParam BookingStatus status){
		return venueService.updateVenueStatus(adminId,venueId,status);
	}
	
	
}
