package com.app.party.genine.entity;

import com.app.party.genine.util.Availability;
import com.app.party.genine.util.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
	private BookingStatus status;
}
