package com.app.party.genine.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "venue_type")
public  class Venue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "ven_id_gen")
	@SequenceGenerator(name = "ven_id_gen", allocationSize = 1, initialValue = 101, sequenceName = "farmHouse_sequence")
	private int id;
}
