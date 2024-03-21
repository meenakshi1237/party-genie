package com.app.party.genine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@Operation(description = "To delete a venue", summary = "venue record will be deleted")
	@ApiResponses(value = { @ApiResponse(description = "Delete a venue record", responseCode = "200"),
				@ApiResponse(description = "Venue id not found", responseCode = "404"),@ApiResponse(description = "admin unauthorized",responseCode = "401")})
	@DeleteMapping("/{admin_id}/{venue_id}")
	public ResponseEntity<?> deleteVenue(@PathVariable int admin_id,@PathVariable int venue_id){
		return venueService.deleteVenue(admin_id, venue_id);
	}
	
	@Operation(description = "To get all venues of particular location", summary = "Venue list belongs to the location will be displayed")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Venues belongs to location displayed"),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content)})
	@GetMapping(value="/{admin_id}/{location}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> findVenueByLocation(@PathVariable int admin_id,@PathVariable String location){
		return venueService.findALLVenueByLocation(admin_id, location);
	}
	
	@Operation(description = "To get all venues of particular admin", summary = "Venue list will be displayed")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Venue Created"),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content)})
	@GetMapping(value="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> findAllVenues(@PathVariable int id){
		return venueService.findAllVenues(id);
	}

	@Operation(description = "To update venue", summary = "Venue will be updated")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Venue updated"),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@PutMapping(value="/{admin_id}/{venue_id}/{venueType}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> updateVenue(@PathVariable String venueType,@PathVariable int admin_id,@PathVariable int venue_id,@RequestBody VenueRequest venueRequest){
		return venueService.updateVenue(venueType, admin_id, venue_id, venueRequest);
	}
	
}
