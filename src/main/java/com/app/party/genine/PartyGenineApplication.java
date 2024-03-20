package com.app.party.genine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.party.genine.entity.Admin;
import com.app.party.genine.repository.AdminRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title = "Partie Genine Application OPEN-API",version = "1.0.0",description = "Partie Genine Application API with Spring boot"),
servers = {
		@Server (url = "http://localhost:8080",description = " Development Partie Genine Application OPEN API url"),
		@Server (url = "http://localhost:8081",description = "Testing Partie Genine Application OPEN API url")
}
)
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
