package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartItemRepository;
import com.app.dao.CartRepository;
import com.app.dao.OrderItemsRepository;
import com.app.dao.OrderRepository;
import com.app.dao.ProductRepository;
import com.app.dao.UserRepository;
import com.app.dto.OrderDto;
import com.app.dto.OrderItemsDto;
import com.app.dto.UserLoginResponse;
import com.app.dto.UserRegResponse;
import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.Order;
import com.app.entities.OrderItems;
import com.app.entities.Product;
import com.app.entities.User;
import com.app.exception.ResourceNotFoundException;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private OrderItemsRepository orderItemsRepo;
	
	@Override
	public List<Order> getOrdersByUser(long userId) {
//		List<Order> order=orderRepo.getOrdersByUserId(userId);
//		OrderDto orderResp = mapper.map(order, OrderDto.class);
//		return orderResp;
		return orderRepo.getOrdersByUserId(userId);
	}

	@Override
	public OrderDto getOrderDetailsById(long orderId) {
		
		
		//orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Invalid Order ID " + orderId));
		//UserLoginResponse resp = mapper.map(user, UserLoginResponse.class);
		Optional<Order> order=orderRepo.findById(orderId);
		OrderDto orderResp = mapper.map(order, OrderDto.class);
		return orderResp;
	}

	@Override
	public OrderDto addOrder(Order order) {
		System.out.println("in add order method "+getClass());
		Order saveOrder=orderRepo.save(order);
		OrderDto orderResp = mapper.map(saveOrder, OrderDto.class);
		return orderResp;
	}
	
	@Override
	public String deleteOrder(long orderId) {
		orderRepo.deleteById(orderId);
		return "Order details deleted for order id " + orderId;
	}
	
	
	
	
//---------------------------OrderItems----------------------------------//

	
	
	
	@Override
	public List<OrderItems> getOrderItemsByUserId(long userId) {
		return orderItemsRepo.getOrderItemsByUserId(userId);
	}

	@Override
	public OrderItems addOrderInOrderItem(@Valid OrderItemsDto orderItem) {
//		OrderDto orderResp = mapper.map(order, OrderDto.class);
//		return orderResp;
		OrderItems order=mapper.map(orderItem, OrderItems.class);
//		long userId=orderItem.getUserId().getId();
//		long productId=orderItem.getProductId().getId();
		User uid = userRepo.findById(orderItem.getUserId()).orElseThrow(null);
		//System.out.println(findById);
		Product pid=productRepo.findById(orderItem.getProductId()).orElseThrow(null);
		
		order.setUserId(uid);
		order.setProductId(pid);
		return orderItemsRepo.save(order);
		
	}

	@Override
	public String deleteOrderItem(long orderItemsId) {
		orderItemsRepo.deleteById(orderItemsId);
		return "Order details deleted for order id " + orderItemsId;
	}

	@Override
	public OrderItems addOrderItemByCartId(long cartId) {
		//OrderItems orderItem=orderItemsRepo.findByCartId(cartId).orElseThrow(() -> new ResourceNotFoundException("Invalid cart id !!!!!!"));
		
		return null;
	}

	

}
