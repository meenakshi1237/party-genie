package com.app.party.genine.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@DiscriminatorColumn(name = "venue_type")
public class Venue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "ven_id_gen")
	@SequenceGenerator(name = "ven_id_gen",initialValue = 101, allocationSize = 1 , sequenceName = "venue_sequence")
	private int id;
}
