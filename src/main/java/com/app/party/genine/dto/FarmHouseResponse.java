package com.app.party.genine.dto;

import com.app.party.genine.util.Availability;
import com.app.party.genine.util.BookingStatus;

import lombok.Data;

@Data
public class FarmHouseResponse {

	private int id;
	
	private int capacity;
	
	private String foodType;
	
	private double rentPerDay;
	
	private Availability parking;
	
	private Availability swimmingPool;
	
	private int noOfRooms;
	
	private String location;
	
	private BookingStatus status;
}
