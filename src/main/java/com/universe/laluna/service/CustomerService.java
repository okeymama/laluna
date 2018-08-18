package com.universe.laluna.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.universe.laluna.dto.CustomerDto;
import com.universe.laluna.dto.OrderDto;
import com.universe.laluna.entity.Customer;
import com.universe.laluna.entity.Order;
import com.universe.laluna.repository.CustomerRepo;
import com.universe.laluna.util.LalunaBeanUtil;

@Transactional
@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	public CustomerDto getCustomer(Long customerId) throws Exception {
			CustomerDto customerDto = null;
			List<OrderDto> orderDtoList = null;
			Customer customer = null;
			customer = customerRepo.findById(customerId).orElseThrow(Exception :: new);
			if(null != customer) {
				customerDto = new CustomerDto();
				LalunaBeanUtil.copyProperties(customer, customerDto);
				orderDtoList = LalunaBeanUtil.copyEntityListToDTOList(customer.getOrder(),OrderDto.class);
				customerDto.setOrderDto(orderDtoList);
			}
		return customerDto;
	}

	public Customer saveCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		Order order = null;
		Set<Order> orderSet = null;
		LalunaBeanUtil.copyProperties(customerDto, customer);
		if(!CollectionUtils.isEmpty(customerDto.getOrderDto())) {
			orderSet = new HashSet<>();
			for(OrderDto orderDto: customerDto.getOrderDto()) {
				order = new Order();
				LalunaBeanUtil.copyProperties(orderDto, order);
				order.setCustomer(customer);
				orderSet.add(order);
			}
			customer.setOrder(orderSet);
		}
		return customerRepo.save(customer);
	}
	

}
