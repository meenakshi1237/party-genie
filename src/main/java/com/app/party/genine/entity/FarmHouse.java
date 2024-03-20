package com.app.party.genine.entity;

import com.app.party.genine.util.Availability;
import com.app.party.genine.util.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("farmhouse")
public class FarmHouse extends Venue{

	@Column(nullable = false)

	private int capacity;
	
	@Column(nullable = false)
	private String foodType;
	
	@Column(nullable = false)
	private double rentPerDay;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Availability parking;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Availability swimmingPool;
	
	@Column(nullable = false)
	private int noOfRooms;
	
	@Column(nullable = false)
	private String location;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private BookingStatus status;
}
