package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.entities.Order;

public interface OrderService {
	
	List<Order> getOrdersByUser (long userId);

	Order getOrderDetailsById(long orderId);

	Order addOrder(@Valid Order order);

	String deleteOrder(long orderId);

}
