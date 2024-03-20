package com.app.party.genine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.party.genine.dao.CustomerDao;
import com.app.party.genine.dto.CustomerRequest;
import com.app.party.genine.dto.CustomerResponse;
import com.app.party.genine.dto.ResponseStructure;
import com.app.party.genine.entity.Customer;
import com.app.party.genine.exceptions.IdNotPresentException;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	public ResponseEntity<ResponseStructure<CustomerResponse>> customerRegister(CustomerRequest customerRequest){
		
		Customer customer=new Customer();
		customer.setEmail(customerRequest.getEmail());
		customer.setIdNumber(customerRequest.getIdNumber());
		customer.setIdProof(customerRequest.getIdProof());
		customer.setName(customerRequest.getName());
		customer.setPassword(customerRequest.getPassword());
		customer.setPhone(customerRequest.getPhone());
		
		
		customer = customerDao.saveCustomer(customer);
		
		
		CustomerResponse customerResponse=new CustomerResponse();
		customerResponse.setId(customer.getId());
		customerResponse.setEmail(customer.getEmail());
		customerResponse.setIdNumber(customer.getIdNumber());
		customerResponse.setIdProof(customer.getIdProof());
		customerResponse.setName(customer.getName());
		customerResponse.setPhone(customer.getPhone());
		
		
		ResponseStructure<CustomerResponse> rs = new ResponseStructure<CustomerResponse>();
		rs.setData(customerResponse);
		rs.setMessage(HttpStatus.CREATED.getReasonPhrase());
		rs.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<CustomerResponse>>(rs,HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<ResponseStructure<CustomerResponse>> updateCustomer(
			CustomerRequest customerRequest,int customerId) {
		
		Customer customer = customerDao.getCustomerById(customerId);
		if(customer != null) {
		if(!customerRequest.getEmail().isBlank()) {
			customer.setEmail(customerRequest.getEmail());
		}
		if(!customerRequest.getIdNumber().isBlank()) {
			customer.setIdNumber(customerRequest.getIdNumber());
		}
		if(!customerRequest.getIdProof().isBlank()) {
			customer.setIdProof(customerRequest.getIdProof());
		}
		if(!customerRequest.getName().isBlank()) {
			customer.setName(customerRequest.getName());
		}
		if(!customerRequest.getPassword().isBlank()) {
			customer.setPassword(customerRequest.getPassword());
		}
		if(customerRequest.getPhone() != 0) {
			customer.setPhone(customerRequest.getPhone());
		}
		
        customer = customerDao.saveCustomer(customer);
		
		
		CustomerResponse customerResponse=new CustomerResponse();
		customerResponse.setId(customer.getId());
		customerResponse.setEmail(customer.getEmail());
		customerResponse.setIdNumber(customer.getIdNumber());
		customerResponse.setIdProof(customer.getIdProof());
		customerResponse.setName(customer.getName());
		customerResponse.setPhone(customer.getPhone());
		
		
		ResponseStructure<CustomerResponse> rs = new ResponseStructure<CustomerResponse>();
		rs.setData(customerResponse);
		rs.setMessage(HttpStatus.OK.getReasonPhrase());
		rs.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<CustomerResponse>>(rs,HttpStatus.OK);
		}else {
			throw new IdNotPresentException("this customer not valid user");
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteCustomer(int id){
		boolean removeCustomer = customerDao.removeCustomer(id);
		
		String message="";
		if(removeCustomer) {
			message="Successfully your account has been deleted";
		}else {
			throw new IdNotPresentException("Account not found,please check customer account is exist");
		}
		
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setMessage(message);
		rs.setData("Account deleted from the DB");
		rs.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.OK);
	}
}
