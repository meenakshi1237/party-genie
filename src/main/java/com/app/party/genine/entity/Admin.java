package com.app.party.genine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "admin_seq_gen")
	@SequenceGenerator(name = "admin_seq_gen", allocationSize = 1, initialValue = 1, sequenceName = "admin_sequence")
	private int id;
	private String name;
	private String email;
	private String password;
	private long phone;
}
