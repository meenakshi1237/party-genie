package com.app.party.genine.dto;

import lombok.Data;

@Data
public class CustomerResponse {
	private int id;
	private String name;
	private long phone;
	private String email;
	private String idProof;
	private String idNumber;
}
