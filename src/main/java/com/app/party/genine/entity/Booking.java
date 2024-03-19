package com.app.party.genine.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "Booking_id_gen")
	@SequenceGenerator(name = "Booking_id_gen", allocationSize = 2001, initialValue = 1, sequenceName = "Booking_sequence")
	private int id;
	@Column(nullable = false)
	private int noOfDays;
	@Column(nullable = false)
	private double totalCharges;
	@Column(nullable = false)
	@OneToOne
	private Venue venue;
	@Column(nullable = false)
	@OneToOne
	private Customer customer;
	@Column(nullable = false)
	private Date eventDate;
	@Column(nullable = false)
	private Date tillDate;
}
