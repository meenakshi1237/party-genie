package com.app.party.genine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.party.genine.dto.VenueRequest;

import com.app.party.genine.exceptions.FeildValidationException;
import com.app.party.genine.service.VenueService;
import com.app.party.genine.util.BookingStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin/venues")
public class VenueController {
	
	@Autowired
	private VenueService venueService;
	
	@Operation(description = "To create venue", summary = "Venue will be created")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Created"),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@PostMapping(value="/{id}/{venueType}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> saveVenue(@Valid @RequestBody VenueRequest venueRequest,BindingResult result,@PathVariable String venueType,@PathVariable int id){
		return venueService.saveVenue(venueType,venueRequest,id,result);
	}
	
	@Operation(description = "To get all venues of particular admin", summary = "Venue list will be displayed")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@GetMapping(value="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> findAllVenues(@PathVariable int id){
		return venueService.findAllVenues(id);
	}
	

	@Operation(description = "To Update the status of venue by Admin", summary = "Venue Status will be Updated")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Ok"),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content),@ApiResponse(responseCode = "400",description = "`BAD REQUEST`", content = @Content)})
	@PatchMapping(value="/{adminId}/{venueId}/{status}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> updateVenueStatus(@PathVariable int adminId,@PathVariable int venueId,@RequestParam BookingStatus status){
		return venueService.updateVenueStatus(adminId,venueId,status);
	}
	
	
}
