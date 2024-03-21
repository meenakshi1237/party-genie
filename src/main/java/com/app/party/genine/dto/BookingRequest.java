package com.app.party.genine.dto;

import java.sql.Date;

import lombok.Data;
@Data
public class BookingRequest {
	
	private int noOfDays;
	private Date eventDate;
	private int venueId;
	private String venueType;
	private int customerId;
}
