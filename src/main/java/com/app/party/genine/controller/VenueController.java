package com.app.party.genine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/admin/venues")
public class VenueController {
	
	@Autowired
	private VenueService venueService;
	
	@Operation(description = "To create venue", summary = "Venue will be created")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Venue Created"),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@PostMapping(value="/{id}/{venueType}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> saveVenue(@PathVariable String venueType,@RequestBody VenueRequest venueRequest,@PathVariable int id){
		return venueService.saveVenue(venueType,venueRequest,id);
	}
	
	@Operation(description = "To get all venues of particular admin", summary = "Venue list will be displayed")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Venue Created"),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content)})
	@GetMapping(value="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> findAllVenues(@PathVariable int id){
		return venueService.findAllVenues(id);
	}
	
	@PatchMapping("/{adminId}/{venueId}/{status}")
	public ResponseEntity<?> updateVenueStatus(@PathVariable int adminId,@PathVariable int venueId,@RequestParam BookingStatus status){
		return venueService.updateVenueStatus(adminId,venueId,status);
	}
	
	
}
