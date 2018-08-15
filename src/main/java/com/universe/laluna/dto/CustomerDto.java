package com.universe.laluna.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerDto {

	private List<OrderDto> order;
    
    private Long customerId;

    private String customerName;

    private String email;
    
    private String phoneNumber;
	
}
