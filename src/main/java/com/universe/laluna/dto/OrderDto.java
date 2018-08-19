package com.universe.laluna.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -717817800527967898L;

	private Long orderId;

    private String orderType;

    private String paymentType;
        	
}
