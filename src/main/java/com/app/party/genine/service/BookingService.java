package com.app.party.genine.service;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.query.ReturnableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.party.genine.dao.BookingDao;
import com.app.party.genine.dto.BookingRequest;
import com.app.party.genine.dto.BookingResponse;
import com.app.party.genine.dto.ResponseStructure;
import com.app.party.genine.entity.Booking;
import com.app.party.genine.entity.Customer;
import com.app.party.genine.entity.FarmHouse;
import com.app.party.genine.entity.PartyHall;
import com.app.party.genine.entity.Venue;
import com.app.party.genine.entity.WeddingHall;
import com.app.party.genine.exceptions.CustomerNotFoundException;
import com.app.party.genine.exceptions.VenueAlreadyBookedException;
import com.app.party.genine.exceptions.VenueNotFoundException;
import com.app.party.genine.repository.CustomerRepository;
import com.app.party.genine.repository.VenueRepository;

import jakarta.persistence.DiscriminatorValue;
@Service
public class BookingService {
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private CustomerRepository customerRepository;
	private VenueRepository<Venue> venueRepository;
	public ResponseEntity<ResponseStructure<Booking>> createBooking(BookingRequest bookingRequest){
		Booking booking=new Booking();
		Customer customer=customerRepository.findById(bookingRequest.getCustomerId()).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
			Venue venue=venueRepository.findById(bookingRequest.getVenueId()).orElseThrow(()->new VenueNotFoundException("Venue Not Found"));
			if(venue instanceof FarmHouse) {
				FarmHouse farmHouse=(FarmHouse)venue;
				if(farmHouse.getStatus().toString().equalsIgnoreCase("available")) {
					booking.setTotalCharges(bookingRequest.getNoOFDays()*farmHouse.getRentPerDay());
				}
				else {
					throw new VenueAlreadyBookedException("Venue is Already Booked");
				}
				
			}
			else if(venue instanceof PartyHall) {
				PartyHall partyHall=(PartyHall)venue;
				if(partyHall.getStatus().toString().equalsIgnoreCase("available")) {
					booking.setTotalCharges((bookingRequest.getNoOFDays()*partyHall.getMenuPricePerPlate()*partyHall.getNoOfPeople()));
				}
				else {
					throw new VenueAlreadyBookedException("Venue is Already Booked");
				}
				
			}
			else {
				WeddingHall weddingHall=(WeddingHall) venue;
				if(weddingHall.getStatus().toString().equalsIgnoreCase("available")) {
					booking.setTotalCharges(bookingRequest.getNoOFDays()*weddingHall.getRentPerDay());
				}
				else {
					throw new VenueAlreadyBookedException("Venue is Already Booked");
				}
				
			}
			booking.setNoOfDays(bookingRequest.getNoOFDays());
			booking.setCustomer(customer);
			booking.setEventDate(bookingRequest.getEventDate());
			booking.setVenue(venue);
			booking.setTillDate(null);
			Date endDate= Date.valueOf(calculateEndDate(bookingRequest.getEventDate().toLocalDate(),bookingRequest.getNoOFDays()));
			booking.setTillDate(endDate);
			
			Booking saveBooking=bookingDao.createBooking(booking);
			ResponseStructure<Booking> responStructure=new ResponseStructure<Booking>();
			responStructure.setData(saveBooking);
			responStructure.setMessage("Your Venue Booked Sucessfully");
			responStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Booking>>(responStructure,HttpStatus.OK);
		
	}
	public  LocalDate calculateEndDate(LocalDate startDate, int numberOfDays) {
		return startDate.plusDays(numberOfDays);
	}
	
//	public ResponseEntity<ResponseStructure<BookingResponse>> updateBooking(BookingRequest bookingRequest,int bookingId){
//		Booking booking=bookingDao.findByBookingId(bookingId);
//	}
}
