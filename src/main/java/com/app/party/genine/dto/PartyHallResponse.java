package com.app.party.genine.dto;

import com.app.party.genine.util.Availability;
import com.app.party.genine.util.BookingStatus;

import lombok.Data;

@Data
public class PartyHallResponse {

	private int id;

	private int capacity;
	
	private String foodType;
	
	private Availability liquor;
	
	private double menuPricePerPlate;
	
	private Availability parking;
	
	private int noOfPeople;
	
	private String location;
	
	private BookingStatus status;
}
