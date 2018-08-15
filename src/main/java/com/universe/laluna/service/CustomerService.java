package com.universe.laluna.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universe.laluna.dto.CustomerDto;
import com.universe.laluna.entity.Customer;
import com.universe.laluna.repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	public Customer getCustomer(Long customerId) {
		try {
			return customerRepo.findById(customerId).orElseThrow(Exception :: new);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Customer saveCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);
		return customerRepo.save(customer);
	}

}
