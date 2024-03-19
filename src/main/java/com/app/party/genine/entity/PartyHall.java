package com.app.party.genine.entity;

import com.app.party.genine.util.Availability;
import com.app.party.genine.util.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("partyhall")
public class PartyHall extends Venue{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "partyHall_seq_gen")
//	@SequenceGenerator(name = "partyHall_seq_gen", allocationSize = 1, initialValue = 1, sequenceName = "partyHall_sequence")
//	private int id;
	@Column(nullable = false)
	private int capacity;
	@Column(nullable = false)
	private String foodType;
	@Column(nullable = false)
	private Availability liquor;
	@Column(nullable = false)
	private double menuPricePerPlate;
	@Column(nullable = false)
	private Availability parking;
	@Column(nullable = false)
	private int noOfPeople;
	@Column(nullable = false)
	private String location;
	@Column(nullable = false)
	private BookingStatus status;
}
