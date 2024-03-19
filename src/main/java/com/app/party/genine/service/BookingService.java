package com.app.party.genine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.party.genine.dao.BookingDao;
@Service
public class BookingService {
	@Autowired
	private BookingDao bookingDao;
}
