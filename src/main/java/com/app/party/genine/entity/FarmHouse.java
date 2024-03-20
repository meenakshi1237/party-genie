package com.app.party.genine.entity;

import com.app.party.genine.util.Availability;
import com.app.party.genine.util.BookingStatus;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("farmhouse")
public class FarmHouse extends Venue{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "farmHouse_seq_gen")
//	@SequenceGenerator(name = "farmHouse_seq_gen", allocationSize = 1, initialValue = 1, sequenceName = "farmHouse_sequence")
//	private int id;
	private int capacity;
	private String foodType;
	private double rentPerDay;
	
	private Availability parking;

	private Availability swimmingPool;
	private int noOfRooms;
	private String location;

	private BookingStatus status;
}
