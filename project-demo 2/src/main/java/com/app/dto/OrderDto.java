package com.app.dto;

import java.sql.Date;

import com.app.entities.User;

import lombok.Data;

@Data
public class OrderDto {
	//private  orderId;
	private User userId;
	private double totalPrice;
	private Date creationDate;
	private String orderStatus;
}
