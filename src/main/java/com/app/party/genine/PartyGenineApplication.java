package com.app.party.genine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.party.genine.entity.Admin;
import com.app.party.genine.repository.AdminRepository;

@SpringBootApplication
public class PartyGenineApplication implements CommandLineRunner {
	
	@Autowired
	private AdminRepository adminRepository;

	public static void main(String[] args) {
		SpringApplication.run(PartyGenineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(adminRepository.count()==0) {
			Admin admin=new Admin();
			admin.setName("Riyas");
			admin.setEmail("riyas@mail.com");
			admin.setPassword("123");
			admin.setPhone(7899546136l);
			adminRepository.save(admin);
		}
	}
	
	  

}
