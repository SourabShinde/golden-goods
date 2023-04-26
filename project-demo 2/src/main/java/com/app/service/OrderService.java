package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.dto.OrderDto;
import com.app.dto.OrderItemsDto;
import com.app.entities.Order;
import com.app.entities.OrderItems;

public interface OrderService {
	
	List<Order> getOrdersByUser (long userId);
	
	OrderDto getOrderDetailsById(long orderId);

	OrderDto addOrder(@Valid Order order);

	String deleteOrder(long orderId);
	
//---------------------------OrderItems----------------------------------//
	
	List<OrderItems> getOrderItemsByUserId (long userId);

	OrderItems addOrderInOrderItem(@Valid OrderItemsDto orderItem);

	String deleteOrderItem(long orderItemsId);
	
	OrderItems addOrderItemByCartId(long cartId);

	
}
