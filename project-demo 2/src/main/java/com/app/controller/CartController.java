package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.UserRepository;
import com.app.entities.Cart;
import com.app.entities.Category;
import com.app.exception.ResourceNotFoundException;
import com.app.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
@Tag(name = "Cart", description = "Cart related functionalities.")
public class CartController {
	@Autowired
	CartService cartService;
	@Autowired
	UserRepository userRepo;
	
	public CartController() {	
	}
	
	@PostMapping("/{userId}")
	@Operation(summary = "Creates a new cart", description = "To add a new cart", tags = {
	"Cart" })
	public ResponseEntity<?> addUserCart(@PathVariable long userId, @RequestBody @Valid Cart cart) {
		cart.setUserId(userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("invalid User")));
		System.out.println("in add category dtls " + cart);
		return new ResponseEntity<>(cartService.addCart(cart), HttpStatus.CREATED);

	}
	
}
