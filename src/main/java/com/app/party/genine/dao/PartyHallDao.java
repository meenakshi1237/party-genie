package com.app.party.genine.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.party.genine.entity.PartyHall;
import com.app.party.genine.repository.PartyHallRepository;

@Component
public class PartyHallDao {
	@Autowired
	private PartyHallRepository partyHallRepository;
	
	public PartyHall save(PartyHall partyHall) {
		return partyHallRepository.save(partyHall);
	}
	
	public String delete(PartyHall partyHall) {
		partyHallRepository.delete(partyHall);
		 return "Party hall deleted successfully!";

	}

	public List<PartyHall> getAll(){
		return partyHallRepository.findAll();

	}

}
