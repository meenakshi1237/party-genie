package com.app.party.genine.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.party.genine.util.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Customer implements UserDetails {
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
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(Role.CUSTOMER.name()));
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
