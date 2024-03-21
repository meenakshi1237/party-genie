package com.app.party.genine.dao;

import java.util.List;

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
	

	public String delete(WeddingHall weddingHall) {
		weddingHallRepository.delete(weddingHall);
		return "Wedding Hall deleted successfully";
	}


	public List<WeddingHall> getAll(){
		return weddingHallRepository.findAll();
	}

}
