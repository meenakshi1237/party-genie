package com.app.party.genine.dto;

import com.app.party.genine.util.Availability;
import com.app.party.genine.util.BookingStatus;


import lombok.Data;

@Data
public class WeddingHallResponse {

	private int id;
	
	private int capacity;
	
	private String foodType;
	
	private Availability liquor;
	
	private double rentPerDay;
	
	private Availability parking;
	
	private String location;
	
	private BookingStatus status;
}
