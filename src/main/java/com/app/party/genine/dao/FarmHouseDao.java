package com.app.party.genine.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.party.genine.entity.FarmHouse;
import com.app.party.genine.repository.FarmHouseRepository;

@Component
public class FarmHouseDao {

	@Autowired
	private FarmHouseRepository farmHouseRepository;

	public FarmHouse save(FarmHouse farmHouse) {
		return farmHouseRepository.save(farmHouse);
	}
	
	public String delete(FarmHouse farmHouse) {
		farmHouseRepository.delete(farmHouse);
		return "FarmHouse Hall deleted successfully";
	}
}
