package com.app.dto;

import lombok.Data;

@Data
public class OrderItemsDto {
	private long userId;
	private long productId;
	private double quantity;
	private double totalBill;
	
	
}
