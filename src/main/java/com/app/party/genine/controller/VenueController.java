package com.app.party.genine.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.party.genine.dto.VenueRequest;

import com.app.party.genine.service.VenueService;

@RestController
@RequestMapping("/admin/venues")
public class VenueController {
	
	@Autowired
	private VenueService venueService;
	
	@PostMapping("/{id}/{venueType}")
	public ResponseEntity<?> saveVenue(@PathVariable String venueType,@RequestBody VenueRequest venueRequest,@PathVariable int id){
		return venueService.saveVenue(venueType,venueRequest,id);
	}
	
	@DeleteMapping("/{admin_id}/{venue_id}")
	public ResponseEntity<?> deleteVenue(@PathVariable int admin_id,@PathVariable int venue_id){
		return venueService.deleteVenue(admin_id, venue_id);
	}
	
	@GetMapping("/{admin_id}/{location}")
	public ResponseEntity<?> findVenueByLocation(@PathVariable int admin_id,@PathVariable String location){
		return venueService.findALLVenueByLocation(admin_id, location);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findAllVenues(@PathVariable int id){
		return venueService.findAllVenues(id);
	}

	@PutMapping("/{admin_id}/{venue_id}/{venueType}")
	public ResponseEntity<?> updateVenue(@PathVariable String venueType,@PathVariable int admin_id,@PathVariable int venue_id,@RequestBody VenueRequest venueRequest){
		return venueService.updateVenue(venueType, admin_id, venue_id, venueRequest);
	}
	
}
