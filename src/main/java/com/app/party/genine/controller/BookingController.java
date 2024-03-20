package com.app.party.genine.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/customer")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	

	@PostMapping(value="/booking")
	public ResponseEntity<ResponseStructure<BookingResponse>> createBooking(@RequestBody BookingRequest bookingRequest){
		return bookingService.createBooking(bookingRequest);
	}
	
	@DeleteMapping(value = "booking")
	public ResponseEntity<ResponseStructure<String>> cancelBooking(@RequestParam int bookingId){
		return bookingService.calcelBooking(bookingId);
	}

}
