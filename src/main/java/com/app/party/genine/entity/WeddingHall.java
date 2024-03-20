package com.app.party.genine.entity;

import com.app.party.genine.util.Availability;
import com.app.party.genine.util.BookingStatus;

import jakarta.persistence.Column;
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
@DiscriminatorValue("weddinghall")
public class WeddingHall extends Venue{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "weddingHall_seq_gen")
//	@SequenceGenerator(name = "weddingHall_seq_gen", allocationSize = 1, initialValue = 1, sequenceName = "weddingHall_sequence")
//	private int id;
	@Column(nullable = false)
	private int capacity;
	@Column(nullable = false)
	private String foodType;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Availability liquor;
	@Column(nullable = false)
	private double rentPerDay;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Availability parking;
	@Column(nullable = false)
	private String location;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private BookingStatus status;
	
	
	
}
