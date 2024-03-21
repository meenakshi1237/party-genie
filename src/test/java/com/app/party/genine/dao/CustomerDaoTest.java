package com.app.party.genine.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.party.genine.entity.Customer;

@SpringBootTest
class CustomerDaoTest {
	
	@Autowired
	private CustomerDao customerDao;

	@Test
	void saveCustomerTest() {
		Customer customer=new Customer();
		customer.setName("Meenakshi Naidu");
		customer.setEmail("meena123@gmail.com");
		customer.setPassword("Meena@123");
		customer.setPhone(7893028502l);
		customer.setIdProof("Aadhar");
		customer.setIdNumber("453256543265");
		
		Customer saveCustomer = customerDao.saveCustomer(customer);
		assertNotNull(saveCustomer);
		assertEquals(customer, saveCustomer);
	}

}
