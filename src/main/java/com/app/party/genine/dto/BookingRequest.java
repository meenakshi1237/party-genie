package com.app.party.genine.dto;

import java.util.Date;

import lombok.Data;
@Data
public class BookingRequest {
	
	private int noOFDays;
	private Date eventDate;
	private int venueId;
	private String venueType;
}
