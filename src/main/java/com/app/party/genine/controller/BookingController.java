package com.app.party.genine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.party.genine.dto.BookingRequest;
import com.app.party.genine.dto.BookingResponse;
import com.app.party.genine.dto.ResponseStructure;
import com.app.party.genine.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/customer")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@Operation(description = "To Create Booking", summary = "Booking will be created")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Booking Created"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@PostMapping(value="/booking",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<BookingResponse>> createBooking(@RequestBody BookingRequest bookingRequest){
		return bookingService.createBooking(bookingRequest);
	}
	
	@Operation(description = "To Cancel Booking", summary = "Booking will be cancled")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Booking Cancled"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@DeleteMapping(value = "booking",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<String>> cancelBooking(@RequestParam int bookingId){
		return bookingService.calcelBooking(bookingId);
	}

}
