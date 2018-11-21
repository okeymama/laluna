package com.universe.laluna.controller;

import java.util.List;

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
import com.universe.laluna.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	 @GetMapping("/getCustomerDetail/{customerId}")
	 public @ResponseBody ResponseEntity<CustomerDto> getCustomer(@PathVariable Long customerId) throws Exception{
		 log.info("Inside CustomerService.getCustomer: "+customerId);
		 CustomerDto customerDto = customerService.getCustomer(customerId);
		 return ResponseEntity.ok(customerDto);
	 }
	 
	 @PostMapping("/saveCustomerDetail")
	 public @ResponseBody String saveCustomer(@RequestBody CustomerDto customerDto){
		 customerService.saveCustomer(customerDto);
		 return "Success";
	 }
	 
	 @GetMapping("/getAllCustomerIds")
	 public @ResponseBody ResponseEntity<List<Long>> getAllCustomerIds() throws Exception{
		 log.info("Inside CustomerService.getAllCustomerIds: ");
		 List<Long> allCustomerIds = customerService.getAllCustomerIds();
		 return ResponseEntity.ok(allCustomerIds);
	 }   
}
