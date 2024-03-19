package com.app.party.genine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.app.party.genine.entity.Venue;

@NoRepositoryBean
public interface VenueRepository<T extends Venue> extends JpaRepository<T, Integer>{

}
