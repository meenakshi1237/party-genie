package com.app.party.genine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "cus_id_gen")
	@SequenceGenerator(name = "cus_id_gen",initialValue = 1001,allocationSize = 1,sequenceName = "cus_seq_gen")
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false,unique = true)
	private long phone;
	@Column(nullable = false,unique = true)
	private String email;
	private String password;
	@Column(nullable = false)
	private String idProof;
	@Column(nullable = false,unique = true)
	private String idNumber;

}
