package com.universe.laluna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.universe.laluna.dto.CustomerDto;
import com.universe.laluna.entity.Customer;
import com.universe.laluna.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	 @GetMapping("/getCustomerDetail/{customerId}")
	 public @ResponseBody ResponseEntity<Customer> getCustomer(@PathVariable Long customerId){
		 Customer customer = customerService.getCustomer(customerId);
		 return ResponseEntity.ok(customer);
	 }
	 
	 @PostMapping("/saveCustomerDetail")
	 public @ResponseBody ResponseEntity<Customer> saveCustomer(@RequestBody CustomerDto customerDto){
		 Customer customerEntity = customerService.saveCustomer(customerDto);
		 return ResponseEntity.ok(customerEntity);
	 }
	 
	      
}
