package com.universe.laluna.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CustomerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8238635599710674545L;

	private List<OrderDto> orderDto;

	private Long customerId;

	private String customerName;

	private String email;

	private String phoneNumber;

}
