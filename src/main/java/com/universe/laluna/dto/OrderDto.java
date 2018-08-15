package com.universe.laluna.dto;

import lombok.Data;

@Data
public class OrderDto {

	private Long orderId;

    private String orderType;

    private String paymentType;
    
    private CustomerDto customer;
    	
}
