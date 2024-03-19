package com.app.party.genine.dto;

import com.app.party.genine.util.Availability;
import com.app.party.genine.util.BookingStatus;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PartyHallRequest {
	
	@PositiveOrZero(message = "No Negatives Allowed")
	private int capacity;
	
	@Pattern(regexp = "^[A-Za-z0-9\\s]*$",message = "Invalid Name Format")
	private String foodType;
	
	
	private Availability liquor;
	
	@PositiveOrZero(message = "No Negatives Allowed")
	private double menuPricePerPlate;
	
	private Availability parking;
	
	@PositiveOrZero(message = "No Negatives Allowed")
	private int noOfPeople;
	
	private String location;
	
	private BookingStatus status;

}
