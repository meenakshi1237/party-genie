package com.app.party.genine.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.aspectj.weaver.PrivilegedAccessMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.party.genine.util.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class Admin implements UserDetails{
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "admin_seq_gen")
	@SequenceGenerator(name = "admin_seq_gen", allocationSize = 1, initialValue = 1, sequenceName = "admin_sequence")
	private int id;
	private String name;
	@Column(unique=true,nullable = false)
	@Email(message = "please enter valid email address")
	private String email;
	@Column(nullable = false)
	private String password;
	@Min(value = 6000000000l,message = "mobile number should srart from 6")
	@Max(value = 9999999999l,message = "mobile number must have 10 numbers")
	private long phone;
	
	@OneToMany
	private List<Venue> venueList=new ArrayList<Venue>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(Role.ADMIN.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public String getPassword() {
		return new BCryptPasswordEncoder().encode(password);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}
