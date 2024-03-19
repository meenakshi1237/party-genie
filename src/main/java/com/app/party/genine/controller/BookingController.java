package com.app.party.genine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.app.party.genine.dto.BookingRequest;
import com.app.party.genine.dto.ResponseStructure;
import com.app.party.genine.entity.Booking;
import com.app.party.genine.service.BookingService;

@RestController
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	public ResponseEntity<ResponseStructure<Booking>> createBooking(BookingRequest bookingRequest){
		return bookingService.createBooking(bookingRequest);
	}
}
