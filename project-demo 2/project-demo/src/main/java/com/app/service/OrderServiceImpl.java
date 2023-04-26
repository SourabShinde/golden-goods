package com.app.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.OrderRepository;
import com.app.dao.ProductRepository;
import com.app.entities.Order;
import com.app.entities.Product;
import com.app.exception.ResourceNotFoundException;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	
	@Autowired
	private OrderRepository orderRepo;
	
	@Override
	public List<Order> getOrdersByUser(long userId) {

		return orderRepo.getOrdersByUserId(userId);
	}

	@Override
	public Order getOrderDetailsById(long orderId) {
		// TODO Auto-generated method stub
		return orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Invalid Order ID " + orderId));
	}

	@Override
	public Order addOrder(Order order) {
		System.out.println("in add order method "+getClass());
		return orderRepo.save(order);
	}
	
	@Override
	public String deleteOrder(long orderId) {
		orderRepo.deleteById(orderId);
		return "Order details deleted for order id " + orderId;
	}

}
