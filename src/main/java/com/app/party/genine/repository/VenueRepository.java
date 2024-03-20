package com.app.party.genine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.party.genine.entity.Venue;

@Repository
public interface VenueRepository<T extends Venue> extends JpaRepository<T, Integer>{

}
