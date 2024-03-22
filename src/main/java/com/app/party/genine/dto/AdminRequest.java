package com.app.party.genine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;


public class AdminRequest {

	@Size(min = 3, message = "password should have atleast 3 characters")
	private String name;
	@Min(value = 6000000000l, message = "mobile number should srart from 6")
	@Max(value = 9999999999l, message = "mobile number must have 10 numbers")
	private long phone;
	@Email(message = "please enter valid email address")
	private String email;
	@Size(min = 5, message = "password should have atleast 5 characters")
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
