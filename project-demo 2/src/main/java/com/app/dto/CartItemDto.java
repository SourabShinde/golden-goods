package com.app.dto;

import lombok.Data;

@Data
public class CartItemDto {
	private long productId;
	private long cart;
	private int quantity;
}
