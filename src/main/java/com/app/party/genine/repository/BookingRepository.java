package com.app.party.genine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.party.genine.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	Booking findByVenueId(int venueId);
}
