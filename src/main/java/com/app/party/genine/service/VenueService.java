package com.app.party.genine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.app.party.genine.dao.AdminDao;
import com.app.party.genine.dao.FarmHouseDao;
import com.app.party.genine.dao.PartyHallDao;
import com.app.party.genine.dao.WeddingHallDao;
import com.app.party.genine.dto.FarmHouseResponse;
import com.app.party.genine.dto.PartyHallResponse;
import com.app.party.genine.dto.ResponseStructure;
import com.app.party.genine.dto.VenueRequest;
import com.app.party.genine.dto.WeddingHallResponse;
import com.app.party.genine.entity.Admin;
import com.app.party.genine.entity.FarmHouse;
import com.app.party.genine.entity.PartyHall;
import com.app.party.genine.entity.Venue;
import com.app.party.genine.entity.WeddingHall;
import com.app.party.genine.exceptions.InvalidVenueException;
import com.app.party.genine.exceptions.UnauthorizedException;

@Service
public class VenueService {

	@Autowired
	private FarmHouseDao farmHouseDao;

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private WeddingHallDao weddingHallDao;
	
	@Autowired
	private PartyHallDao partyHallDao;

	public ResponseEntity<?> saveVenue(String venueType, VenueRequest venueRequest,int id) {
		
		Optional<Admin> validAdmin=adminDao.findAdmin(id);
		
		List<Venue> venueList=new ArrayList<Venue>();
		
		if(validAdmin.isPresent()) {
		
		if(venueType.equalsIgnoreCase("farm house")){
			
			FarmHouse farmHouse=new FarmHouse();
			farmHouse.setCapacity(venueRequest.getCapacity());
			farmHouse.setFoodType(venueRequest.getFoodType());
			farmHouse.setRentPerDay(venueRequest.getRentPerDay());
			farmHouse.setParking(venueRequest.getParking());
			farmHouse.setSwimmingPool(venueRequest.getSwimmingPool());
			farmHouse.setNoOfRooms(venueRequest.getNoOfRooms());
			farmHouse.setLocation(venueRequest.getLocation());
			farmHouse.setStatus(venueRequest.getStatus());
			
			FarmHouse savedFarmHouse=farmHouseDao.save(farmHouse);
			
			venueList.add(savedFarmHouse);
			validAdmin.get().setVenueList(venueList);
			adminDao.save(validAdmin);
			
			FarmHouseResponse farmHouseResponse=new FarmHouseResponse();
			farmHouseResponse.setCapacity(savedFarmHouse.getCapacity());
			farmHouseResponse.setFoodType(savedFarmHouse.getFoodType());
			farmHouseResponse.setRentPerDay(savedFarmHouse.getRentPerDay());
			farmHouseResponse.setParking(savedFarmHouse.getParking());
			farmHouseResponse.setSwimmingPool(savedFarmHouse.getSwimmingPool());
			farmHouseResponse.setNoOfRooms(savedFarmHouse.getNoOfRooms());
			farmHouseResponse.setLocation(savedFarmHouse.getLocation());
			farmHouseResponse.setStatus(savedFarmHouse.getStatus());
			
			ResponseStructure<FarmHouseResponse> response=new ResponseStructure<FarmHouseResponse>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setData(farmHouseResponse);
			response.setMessage("Saved Successfully");
			
			return new ResponseEntity<ResponseStructure<FarmHouseResponse>>(response,HttpStatus.CREATED);
			
		}else if(venueType.equalsIgnoreCase("wedding hall")) {
			
			WeddingHall weddingHall=new WeddingHall();
			weddingHall.setCapacity(venueRequest.getCapacity());
			weddingHall.setFoodType(venueRequest.getFoodType());
			weddingHall.setRentPerDay(venueRequest.getRentPerDay());
			weddingHall.setParking(venueRequest.getParking());
			weddingHall.setLiquor(venueRequest.getLiquor());
			weddingHall.setLocation(venueRequest.getLocation());
			weddingHall.setStatus(venueRequest.getStatus());
			
			
			
			WeddingHall savedWeddingHall=weddingHallDao.save(weddingHall);
			
			venueList.add(savedWeddingHall);
			validAdmin.get().setVenueList(venueList);
			adminDao.save(validAdmin);
			
			WeddingHallResponse weddingHallResponse=new WeddingHallResponse();
			weddingHallResponse.setCapacity(savedWeddingHall.getCapacity());
			weddingHallResponse.setFoodType(savedWeddingHall.getFoodType());
			weddingHallResponse.setRentPerDay(savedWeddingHall.getRentPerDay());
			weddingHallResponse.setParking(savedWeddingHall.getParking());
			weddingHallResponse.setLiquor(savedWeddingHall.getLiquor());
			weddingHallResponse.setLocation(savedWeddingHall.getLocation());
			weddingHallResponse.setStatus(savedWeddingHall.getStatus());
			
			ResponseStructure<WeddingHallResponse> response=new ResponseStructure<WeddingHallResponse>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setData(weddingHallResponse);
			response.setMessage("Saved Successfully");
			
			return new ResponseEntity<ResponseStructure<WeddingHallResponse>>(response,HttpStatus.CREATED);
			
		}else if(venueType.equalsIgnoreCase("party hall")) {
			PartyHall partyHall=new PartyHall();
			partyHall.setCapacity(venueRequest.getCapacity());
			partyHall.setFoodType(venueRequest.getFoodType());
			partyHall.setMenuPricePerPlate(venueRequest.getMenuPricePerPlate());
			partyHall.setNoOfPeople(venueRequest.getNoOfPeople());
			partyHall.setParking(venueRequest.getParking());
			partyHall.setLiquor(venueRequest.getLiquor());
			partyHall.setLocation(venueRequest.getLocation());
			partyHall.setStatus(venueRequest.getStatus());
			
			PartyHall savedPartyHall=partyHallDao.save(partyHall);
			
			venueList.add(savedPartyHall);
			validAdmin.get().setVenueList(venueList);
			adminDao.save(validAdmin);
			
			PartyHallResponse partyHallResponse=new PartyHallResponse();
			partyHallResponse.setCapacity(savedPartyHall.getCapacity());
			partyHallResponse.setFoodType(savedPartyHall.getFoodType());
			partyHallResponse.setMenuPricePerPlate(savedPartyHall.getMenuPricePerPlate());
			partyHallResponse.setNoOfPeople(savedPartyHall.getNoOfPeople());
			partyHallResponse.setParking(savedPartyHall.getParking());
			partyHallResponse.setLiquor(savedPartyHall.getLiquor());
			partyHallResponse.setLocation(savedPartyHall.getLocation());
			partyHallResponse.setStatus(savedPartyHall.getStatus());
			
			ResponseStructure<PartyHallResponse> response=new ResponseStructure<PartyHallResponse>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setData(partyHallResponse);
			response.setMessage("Saved Successfully");
			
			return new ResponseEntity<ResponseStructure<PartyHallResponse>>(response,HttpStatus.CREATED);
		}else {
			throw new InvalidVenueException();
		}	
	}else {
		throw new UnauthorizedException();
	}
			
	}

	public ResponseEntity<?> findAllVenues(int id) {
		
		Optional<Admin> validAdmin=adminDao.findAdmin(id);
		
		if(validAdmin.isPresent()) {
			List<Venue> venueList=validAdmin.get().getVenueList();
			
			ResponseStructure<List<Venue>> response=new ResponseStructure<List<Venue>>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Fethed Successfully");
			response.setData(venueList);
			
			return new ResponseEntity<ResponseStructure<List<Venue>>>(response,HttpStatus.FOUND);
			
		}else {
			throw new UnauthorizedException();
		}
	}
	
	

}
