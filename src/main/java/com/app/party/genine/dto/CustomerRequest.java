package com.app.party.genine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequest {
	@Size(min = 3, message = "password should have atleast 3 characters")
	private String name;
	@Min(value = 6000000000l, message = "mobile number should srart from 6")
	@Max(value = 9999999999l, message = "mobile number must have 10 numbers")
	private long phone;
	@Email(message = "please enter valid email address")
	private String email;
	@Size(min = 5, message = "password should have atleast 5 characters")
	private String password;
	private String idProof;
	private String idNumber;
}
