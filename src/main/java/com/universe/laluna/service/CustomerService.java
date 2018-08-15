package com.universe.laluna.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.universe.laluna.dto.CustomerDto;
import com.universe.laluna.dto.OrderDto;
import com.universe.laluna.entity.Customer;
import com.universe.laluna.entity.Order;
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
		Order order = null;
		Set<Order> orderSet = null;
		BeanUtils.copyProperties(customerDto, customer);
		if(!CollectionUtils.isEmpty(customerDto.getOrderDto())) {
			orderSet = new HashSet<>();
			for(OrderDto orderDto: customerDto.getOrderDto()) {
				order = new Order();
				BeanUtils.copyProperties(orderDto, order);
				order.setCustomer(customer);
				orderSet.add(order);
			}
			customer.setOrder(orderSet);
		}
		return customerRepo.save(customer);
	}

}
