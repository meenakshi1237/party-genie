package com.app.party.genine.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.party.genine.dao.BookingDao;
import com.app.party.genine.dao.CustomerDao;
import com.app.party.genine.dto.BookingRequest;
import com.app.party.genine.dto.BookingResponse;
import com.app.party.genine.dto.CustomerResponse;
import com.app.party.genine.dto.ResponseStructure;
import com.app.party.genine.entity.Booking;
import com.app.party.genine.entity.Customer;
import com.app.party.genine.entity.FarmHouse;
import com.app.party.genine.entity.PartyHall;
import com.app.party.genine.entity.Venue;
import com.app.party.genine.entity.WeddingHall;
import com.app.party.genine.exceptions.BookingNotFoundException;
import com.app.party.genine.exceptions.CustomerNotFoundException;
import com.app.party.genine.exceptions.VenueAlreadyBookedException;
import com.app.party.genine.exceptions.VenueNotFoundException;
import com.app.party.genine.repository.CustomerRepository;
import com.app.party.genine.repository.VenueRepository;
@Service
public class BookingService {
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private VenueRepository<Venue> venueRepository;
	@Autowired
	private CustomerDao customerDao;
	
	public ResponseEntity<ResponseStructure<BookingResponse>> createBooking(BookingRequest bookingRequest){
		Booking booking=new Booking();
		Customer customer=customerDao.getCustomerById(bookingRequest.getCustomerId());
		if(customer!=null) {
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
			Date endDate= Date.valueOf(calculateEndDate(bookingRequest.getEventDate().toLocalDate(),bookingRequest.getNoOFDays()));
			booking.setTillDate(endDate);
			
			Booking saveBooking=bookingDao.createBooking(booking);
			
			CustomerResponse customerResponse=new CustomerResponse();
			customerResponse.setName(customer.getName());
			customerResponse.setEmail(customer.getEmail());
			customerResponse.setId(customer.getId());
			customerResponse.setIdNumber(customer.getIdNumber());
			customerResponse.setIdProof(customer.getIdProof());
			
			BookingResponse bookingResponse=new BookingResponse();
			bookingResponse.setBookingId(saveBooking.getId());
			bookingResponse.setCustomerResponse(customerResponse);
			bookingResponse.setEventDate(saveBooking.getEventDate());
			bookingResponse.setNoOfDays(saveBooking.getNoOfDays());
			bookingResponse.setTillDate(saveBooking.getTillDate());
			bookingResponse.setTotalCharges(saveBooking.getTotalCharges());
			bookingResponse.setVenue(saveBooking.getVenue());
			
			
			ResponseStructure<BookingResponse> responStructure=new ResponseStructure<BookingResponse>();
			responStructure.setData(bookingResponse);
			responStructure.setMessage("Your Venue Booked Sucessfully");
			responStructure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<BookingResponse>>(responStructure,HttpStatus.CREATED);
		
		}
		else {
			throw new CustomerNotFoundException("Customer Not Found");
		}
			
	}
	public  LocalDate calculateEndDate(LocalDate startDate, int numberOfDays) {
//		startDate.plusDays(numberOfDays);
		return startDate.plusDays(numberOfDays-1);
	}
	
	public ResponseEntity<ResponseStructure<String>> calcelBooking(int bookingId){
		Booking booking=bookingDao.findByBookingId(bookingId);
		if(booking!=null) {
			boolean status=bookingDao.cancelBooking(booking);
			if(status) {
				ResponseStructure<String> responseStructure=new ResponseStructure<String>();
				responseStructure.setData("Boooking canceled sucessfully");
				responseStructure.setMessage("Sucess");
				responseStructure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
			}
			else {
				ResponseStructure<String> responseStructure=new ResponseStructure<String>();
				responseStructure.setData("Something went wrong");
				responseStructure.setMessage("please try after sometime");
				responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
				return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST);
			}
			
		}
		else {
			throw new BookingNotFoundException("There is no bookings with given id");
		}
	}
	
	
}
