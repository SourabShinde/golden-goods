package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.CartItemRepository;
import com.app.dao.CartRepository;
import com.app.dao.OrderItemsRepository;
import com.app.dto.ApiResponse;
import com.app.dto.OrderItemsDto;
import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.Order;
import com.app.entities.OrderItems;
import com.app.entities.User;
import com.app.exception.ResourceNotFoundException;
import com.app.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/orderItems")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "OrderItem", description = "OrderItems related functionalities.")
public class OrderItemsController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemsRepository orderItemRepo;
	
	@Autowired
	private CartItemRepository cartItemRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	public OrderItemsController() {
		System.out.println("in ordersItems constructor " + getClass());
	} 
	
	@GetMapping("/{userId}")
	@Operation(summary = "Shows all OrdersItems of a particular user.", description = "To retrieve the order items listed for  a user(id).", tags = {
	"OrderItems By user Id" })
	public ResponseEntity<?> getOrdersByUserId(@PathVariable long userId) {
		System.out.println("in get all order history " + getClass());
		return ResponseEntity.ok(orderService.getOrderItemsByUserId(userId));
	}
	
	@PostMapping("/{userId}/addOrderItem")
	@Operation(summary = "Creates add a new order i order Item", description = "To create a new order", tags = {
	"Order" })
	public ResponseEntity<?> addOrderDetails(@RequestBody @Valid OrderItemsDto orderItem) {
		System.out.println("in add dtls " + orderItem);
		return new ResponseEntity<>(orderService.addOrderInOrderItem(orderItem), HttpStatus.CREATED);

	}	
	
	@PostMapping("/{cartId}")
	public ResponseEntity<?> addOrderDetails(@PathVariable long cartId){
		
		System.out.println();
		User userId=cartRepo.findUserId(cartId);
		OrderItems orderItem=new OrderItems();
		orderItem.setUserId(userId);
		orderItemRepo.save(orderItem);
		
		
//		cartItem.setProductId(c.get);
		return new ResponseEntity<>(orderService.addOrderItemByCartId(cartId),HttpStatus.CREATED);
	}
	
	
	
	
	
	@DeleteMapping("/{userId}/{orderItemsId}/delete")
	@Operation(summary = "Deletes a order ", description = "To delete a order by orderid ", tags = {
	"Order" })
	public ResponseEntity<?> deleteOrderItem(@PathVariable long orderItemsId) {
		System.out.println("in delete order " + orderItemsId);
		return ResponseEntity.ok(new ApiResponse(orderService.deleteOrderItem(orderItemsId)));

	}
	
}
