package com.app.party.genine.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.party.genine.repository.BookingRepository;

@Repository
public class BookingDao {
	@Autowired
	private BookingRepository bookingRepository;
}
