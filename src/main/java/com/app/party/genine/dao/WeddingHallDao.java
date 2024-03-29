package com.app.party.genine.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.party.genine.entity.WeddingHall;
import com.app.party.genine.repository.WeddingHallRepository;

@Component
public class WeddingHallDao {
	
	@Autowired
	private WeddingHallRepository weddingHallRepository;

	public WeddingHall save(WeddingHall weddingHall) {
		return weddingHallRepository.save(weddingHall);
	}

}
