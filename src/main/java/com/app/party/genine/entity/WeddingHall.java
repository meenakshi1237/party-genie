package com.app.party.genine.entity;

import com.app.party.genine.util.Availability;
import com.app.party.genine.util.BookingStatus;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("weddinghall")
public class WeddingHall extends Venue{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "weddingHall_seq_gen")
//	@SequenceGenerator(name = "weddingHall_seq_gen", allocationSize = 1, initialValue = 1, sequenceName = "weddingHall_sequence")
//	private int id;
	private int capacity;
	private String foodType;
	private Availability liquor;
	private double rentPerDay;
	private Availability parking;
	private String location;
	private BookingStatus status;
	
	
}
