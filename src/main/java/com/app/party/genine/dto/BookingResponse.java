package com.app.party.genine.dto;

import java.util.Date;

import com.app.party.genine.entity.Venue;

import lombok.Data;
@Data
public class BookingResponse {
	
	private int bookingId;
	private int noOfDays;
	private double totalCharges;
	private Venue venue;
	private CustomerResponse customerResponse;
	private Date eventDate;
	private Date tillDate ;
}
