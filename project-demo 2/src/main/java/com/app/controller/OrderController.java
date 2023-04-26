package com.app.controller;

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

import com.app.dto.ApiResponse;
import com.app.entities.Order;
import com.app.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Order", description = "Order related functionalities.")
public class OrderController {

	@Autowired
	private OrderService orderService;

	public OrderController() {
		System.out.println("in orders constructor " + getClass());
	}

	@GetMapping("/{userId}")
	@Operation(summary = "Shows all Orders of a particular user.", description = "To retrieve all the orders listed for  a user(id).", tags = {
	"Order" })
	public ResponseEntity<?> getOrdersByUserId(@PathVariable long userId) {
		System.out.println("in get all order history " + getClass());
		return ResponseEntity.ok(orderService.getOrdersByUser(userId));
	}

	@GetMapping("/order/{orderId}")
	@Operation(summary = "Shows order details using order id.", description = "To retrieve  the order details by order(id).", tags = {
	"Order" })
	public ResponseEntity<?> getOrderDetails(@PathVariable long orderId) {
		System.out.println("in get order details " + orderId);
		return ResponseEntity.ok(orderService.getOrderDetailsById(orderId));

	}

	@PostMapping
	@Operation(summary = "Creates a new order", description = "To create a new order", tags = {
	"Order" })
	public ResponseEntity<?> addOrderDetails(@RequestBody @Valid Order order) {
		System.out.println("in add dtls " + order);
		return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);

	}

	@DeleteMapping("/{orderId}")
	@Operation(summary = "Deletes a order ", description = "To delete a order by orderid ", tags = {
	"Order" })
	public ResponseEntity<?> deleteOrderDetails(@PathVariable long orderId) {
		System.out.println("in delete order " + orderId);
		return ResponseEntity.ok(new ApiResponse(orderService.deleteOrder(orderId)));

	}

}
