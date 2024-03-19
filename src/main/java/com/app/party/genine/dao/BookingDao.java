package com.app.party.genine.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.party.genine.entity.Booking;
import com.app.party.genine.repository.BookingRepository;

@Repository
public class BookingDao {
	@Autowired
	private BookingRepository bookingRepository;
	
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	public Booking findByVenueId(int venueId) {
		return bookingRepository.findByVenueId(venueId);
	}
	
	public Booking findByBookingId(int bookingId) {
		Optional<Booking> opt=bookingRepository.findById(bookingId);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			return null;
		}
	}
}
