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

	public ResponseEntity<?> findALLVenueByLocation(int id, String location) {

		Admin admin = adminDao.findAdmin(id).orElseThrow(() -> new UnauthorizedException("You're not an admin"));

		List<Venue> avl_venues = admin.getVenueList();

		List<Venue> loc_venues = new ArrayList<Venue>();

		if (!avl_venues.isEmpty()) {
			for (Venue v : avl_venues) {
				if (v instanceof PartyHall) {
					PartyHall p = (PartyHall) v;
					if (p.getLocation().equalsIgnoreCase(location)) {
						loc_venues.add(p);
					}
				} else if (v instanceof WeddingHall) {
					WeddingHall w = (WeddingHall) v;
					if (w.getLocation().equalsIgnoreCase(location)) {
						loc_venues.add(w);
					}

				} else if (v instanceof FarmHouse) {
					FarmHouse f = (FarmHouse) v;
					if (f.getLocation().equalsIgnoreCase(location)) {
						loc_venues.add(f);
					}

				}
				else {
					throw new InvalidVenueException("Venue at "+location+" not found" );
				}

			}
		}

		ResponseStructure<List<Venue>> response = new ResponseStructure<List<Venue>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(loc_venues);
		response.setMessage(HttpStatus.OK.getReasonPhrase());

		return new ResponseEntity<ResponseStructure<List<Venue>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteVenue(int id, int vid) {
		Optional<Admin> opt = adminDao.findAdmin(id);
		String message = null;
		if (opt.isPresent()) {
			Admin admin = opt.get();
			List<Venue> avl_venues = admin.getVenueList();
			for (Venue v : avl_venues) {
				if (v instanceof PartyHall) {
					PartyHall p = (PartyHall) v;
					if (p.getId() == vid) {

						avl_venues.remove(v);
						adminDao.updateAdminsVenue(admin);

						message = partyHallDao.delete(p);

						ResponseStructure<PartyHall> response = new ResponseStructure<PartyHall>();
						response.setStatusCode(HttpStatus.OK.value());
						response.setData(p);
						response.setMessage(message);

						return new ResponseEntity<ResponseStructure<PartyHall>>(response, HttpStatus.OK);
					} else {
						continue;
					}

				} else if (v instanceof WeddingHall) {
					WeddingHall w = (WeddingHall) v;
					if (w.getId() == vid) {

						avl_venues.remove(w);
						adminDao.updateAdminsVenue(admin);

						message = weddingHallDao.delete(w);
						ResponseStructure<WeddingHall> response = new ResponseStructure<WeddingHall>();
						response.setStatusCode(HttpStatus.OK.value());
						response.setData(w);
						response.setMessage(message);

						return new ResponseEntity<ResponseStructure<WeddingHall>>(response, HttpStatus.OK);
					} else {
						continue;
					}
				} else if (v instanceof FarmHouse) {
					FarmHouse f = (FarmHouse) v;
					if (f.getId() == vid) {

						avl_venues.remove(f);
						adminDao.updateAdminsVenue(admin);

						message = farmHouseDao.delete(f);
						ResponseStructure<FarmHouse> response = new ResponseStructure<FarmHouse>();
						response.setStatusCode(HttpStatus.OK.value());
						response.setData(f);
						response.setMessage(message);

						return new ResponseEntity<ResponseStructure<FarmHouse>>(response, HttpStatus.OK);
					} else {
						continue;
					}
				}
			}
		} else {
			throw new UnauthorizedException();
		}
		throw new InvalidVenueException();
	}

	public ResponseEntity<?> updateVenue(String venueType, int aid, int vid, VenueRequest venueRequest) {
		Optional<Admin> admin = adminDao.findAdmin(aid);
		String message = null;
//		ResponseStructure<Venue> response = new ResponseStructure<Venue>();
		if (admin.isPresent()) {
			Admin a = admin.get();
			for (Venue v : a.getVenueList()) {
				if (v instanceof FarmHouse) {
					FarmHouse f = (FarmHouse) v;
					if (f.getId() == vid) {
						f.setCapacity(venueRequest.getCapacity());
						f.setFoodType(venueRequest.getFoodType());
						f.setNoOfRooms(venueRequest.getNoOfRooms());
						f.setParking(venueRequest.getParking());
						f.setRentPerDay(venueRequest.getRentPerDay());
						f.setLocation(venueRequest.getLocation());
						f.setSwimmingPool(venueRequest.getSwimmingPool());
						f.setStatus(venueRequest.getStatus());

						farmHouseDao.save(f);
						message = venueType + " updated succesfully";

						ResponseStructure<FarmHouse> response = new ResponseStructure<FarmHouse>();
						response.setStatusCode(HttpStatus.OK.value());
						response.setMessage(message);
						response.setData(f);

						return new ResponseEntity<ResponseStructure<FarmHouse>>(response, HttpStatus.OK);
					} else {
						continue;
					}
				} else if (v instanceof PartyHall) {
					PartyHall p = (PartyHall) v;
					if (p.getId() == vid) {
						p.setCapacity(venueRequest.getCapacity());
						p.setFoodType(venueRequest.getFoodType());
						p.setLiquor(venueRequest.getLiquor());
						p.setParking(venueRequest.getParking());
						p.setMenuPricePerPlate(venueRequest.getMenuPricePerPlate());
						p.setLocation(venueRequest.getLocation());
						p.setNoOfPeople(venueRequest.getNoOfPeople());
						p.setStatus(venueRequest.getStatus());

						partyHallDao.save(p);
						message = venueType + " updated succesfully";

						ResponseStructure<PartyHall> response = new ResponseStructure<PartyHall>();
						response.setStatusCode(HttpStatus.OK.value());
						response.setMessage(message);
						response.setData(p);

						return new ResponseEntity<ResponseStructure<PartyHall>>(response, HttpStatus.OK);
					} else {
						continue;
					}
				} else if (v instanceof WeddingHall) {
					WeddingHall w = (WeddingHall) v;
					if (w.getId() == vid) {
						w.setCapacity(venueRequest.getCapacity());
						w.setFoodType(venueRequest.getFoodType());
						w.setLiquor(venueRequest.getLiquor());
						w.setParking(venueRequest.getParking());
						w.setRentPerDay(venueRequest.getRentPerDay());
						w.setLocation(venueRequest.getLocation());
						w.setStatus(venueRequest.getStatus());

						weddingHallDao.save(w);
						message = venueType + " updated succesfully";

						ResponseStructure<WeddingHall> response = new ResponseStructure<WeddingHall>();
						response.setStatusCode(HttpStatus.OK.value());
						response.setMessage(message);
						response.setData(w);

						return new ResponseEntity<ResponseStructure<WeddingHall>>(response, HttpStatus.OK);
					} else {
						continue;
					}

				} else {
					throw new InvalidVenueException();
				}
			}
		} else {
			throw new UnauthorizedException();
		}
		return null;

	}

	public ResponseEntity<?> saveVenue(String venueType, VenueRequest venueRequest, int id) {

		Optional<Admin> validAdmin = adminDao.findAdmin(id);

		if (validAdmin.isPresent()) {

			List<Venue> venueList = validAdmin.get().getVenueList();

			if (venueType.equalsIgnoreCase("farm house")) {

				FarmHouse farmHouse = new FarmHouse();
				farmHouse.setCapacity(venueRequest.getCapacity());
				farmHouse.setFoodType(venueRequest.getFoodType());
				farmHouse.setRentPerDay(venueRequest.getRentPerDay());
				farmHouse.setParking(venueRequest.getParking());
				farmHouse.setSwimmingPool(venueRequest.getSwimmingPool());
				farmHouse.setNoOfRooms(venueRequest.getNoOfRooms());
				farmHouse.setLocation(venueRequest.getLocation());
				farmHouse.setStatus(venueRequest.getStatus());

				FarmHouse savedFarmHouse = farmHouseDao.save(farmHouse);

				venueList.add(savedFarmHouse);
				validAdmin.get().setVenueList(venueList);
				adminDao.save(validAdmin);

				FarmHouseResponse farmHouseResponse = new FarmHouseResponse();
				farmHouseResponse.setCapacity(savedFarmHouse.getCapacity());
				farmHouseResponse.setFoodType(savedFarmHouse.getFoodType());
				farmHouseResponse.setRentPerDay(savedFarmHouse.getRentPerDay());
				farmHouseResponse.setParking(savedFarmHouse.getParking());
				farmHouseResponse.setSwimmingPool(savedFarmHouse.getSwimmingPool());
				farmHouseResponse.setNoOfRooms(savedFarmHouse.getNoOfRooms());
				farmHouseResponse.setLocation(savedFarmHouse.getLocation());
				farmHouseResponse.setStatus(savedFarmHouse.getStatus());

				ResponseStructure<FarmHouseResponse> response = new ResponseStructure<FarmHouseResponse>();
				response.setStatusCode(HttpStatus.CREATED.value());
				response.setData(farmHouseResponse);
				response.setMessage("Saved Successfully");

				return new ResponseEntity<ResponseStructure<FarmHouseResponse>>(response, HttpStatus.CREATED);

			} else if (venueType.equalsIgnoreCase("wedding hall")) {

				WeddingHall weddingHall = new WeddingHall();
				weddingHall.setCapacity(venueRequest.getCapacity());
				weddingHall.setFoodType(venueRequest.getFoodType());
				weddingHall.setRentPerDay(venueRequest.getRentPerDay());
				weddingHall.setParking(venueRequest.getParking());
				weddingHall.setLiquor(venueRequest.getLiquor());
				weddingHall.setLocation(venueRequest.getLocation());
				weddingHall.setStatus(venueRequest.getStatus());

				WeddingHall savedWeddingHall = weddingHallDao.save(weddingHall);

				venueList.add(savedWeddingHall);
				validAdmin.get().setVenueList(venueList);
				adminDao.save(validAdmin);

				WeddingHallResponse weddingHallResponse = new WeddingHallResponse();
				weddingHallResponse.setCapacity(savedWeddingHall.getCapacity());
				weddingHallResponse.setFoodType(savedWeddingHall.getFoodType());
				weddingHallResponse.setRentPerDay(savedWeddingHall.getRentPerDay());
				weddingHallResponse.setParking(savedWeddingHall.getParking());
				weddingHallResponse.setLiquor(savedWeddingHall.getLiquor());
				weddingHallResponse.setLocation(savedWeddingHall.getLocation());
				weddingHallResponse.setStatus(savedWeddingHall.getStatus());

				ResponseStructure<WeddingHallResponse> response = new ResponseStructure<WeddingHallResponse>();
				response.setStatusCode(HttpStatus.CREATED.value());
				response.setData(weddingHallResponse);
				response.setMessage("Saved Successfully");

				return new ResponseEntity<ResponseStructure<WeddingHallResponse>>(response, HttpStatus.CREATED);

			} else if (venueType.equalsIgnoreCase("party hall")) {
				PartyHall partyHall = new PartyHall();
				partyHall.setCapacity(venueRequest.getCapacity());
				partyHall.setFoodType(venueRequest.getFoodType());
				partyHall.setMenuPricePerPlate(venueRequest.getMenuPricePerPlate());
				partyHall.setNoOfPeople(venueRequest.getNoOfPeople());
				partyHall.setParking(venueRequest.getParking());
				partyHall.setLiquor(venueRequest.getLiquor());
				partyHall.setLocation(venueRequest.getLocation());
				partyHall.setStatus(venueRequest.getStatus());

				PartyHall savedPartyHall = partyHallDao.save(partyHall);

				venueList.add(savedPartyHall);
				validAdmin.get().setVenueList(venueList);
				adminDao.save(validAdmin);

				PartyHallResponse partyHallResponse = new PartyHallResponse();
				partyHallResponse.setCapacity(savedPartyHall.getCapacity());
				partyHallResponse.setFoodType(savedPartyHall.getFoodType());
				partyHallResponse.setMenuPricePerPlate(savedPartyHall.getMenuPricePerPlate());
				partyHallResponse.setNoOfPeople(savedPartyHall.getNoOfPeople());
				partyHallResponse.setParking(savedPartyHall.getParking());
				partyHallResponse.setLiquor(savedPartyHall.getLiquor());
				partyHallResponse.setLocation(savedPartyHall.getLocation());
				partyHallResponse.setStatus(savedPartyHall.getStatus());

				ResponseStructure<PartyHallResponse> response = new ResponseStructure<PartyHallResponse>();
				response.setStatusCode(HttpStatus.CREATED.value());
				response.setData(partyHallResponse);
				response.setMessage("Saved Successfully");

				return new ResponseEntity<ResponseStructure<PartyHallResponse>>(response, HttpStatus.CREATED);
			} else {
				throw new InvalidVenueException();
			}
		} else {
			throw new UnauthorizedException();
		}

	}

	public ResponseEntity<?> findAllVenues(int id) {

		Optional<Admin> validAdmin = adminDao.findAdmin(id);

		if (validAdmin.isPresent()) {
			List<Venue> venueList = validAdmin.get().getVenueList();

			ResponseStructure<List<Venue>> response = new ResponseStructure<List<Venue>>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Fethed Successfully");
			response.setData(venueList);

			return new ResponseEntity<ResponseStructure<List<Venue>>>(response, HttpStatus.FOUND);

		} else {
			throw new UnauthorizedException();
		}
	}

}