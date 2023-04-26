package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.CartRepository;
import com.app.dao.ProductRepository;
import com.app.dto.ApiResponse;
import com.app.dto.CartItemDto;
import com.app.entities.CartItem;
import com.app.exception.ResourceNotFoundException;
import com.app.service.CartItemService;
import com.app.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
@Tag(name = "CartItem", description = "Cart Item related functionalities.")
@SecurityRequirement(name = "app-security")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CartRepository cartRepo;

	public CartItemController() {
		System.out.println("in cart item cnstr" + getClass());
	}

	@GetMapping("/{cartId}")
	@Operation(summary = "Shows all th cart items under a particular cartId.", description = "To retrieve the list of cart items under cart(id).", tags = {
	"CartItem" })
	public ResponseEntity<?> listAllCartItems(@PathVariable long cartId) {
		System.out.println("in get all cart Items");
		return ResponseEntity.ok(cartItemService.getCartItemById(cartId));
	}
	
	@GetMapping("/{userId}/cart")
	@Operation(summary = "Shows all the cart items under a particular cartId.", description = "To retrieve the list of cart items under cart(id).", tags = {
	"CartItem" })
	public ResponseEntity<?> getCartItemsDetails(@PathVariable long userId) {
		System.out.println("in get all cart Items");
		return ResponseEntity.ok(userService.getCartItemsByUserId(userId));
	}

	@PostMapping("/addToCart/{cartId}/{productId}")
	@Operation(summary = "Adds a new item in the cart", description = "To add a new cart item in a user cart", tags = {
	"CartItem" })
	public ResponseEntity<?> addNewCartItem(@PathVariable long cartId,@PathVariable long productId,@RequestBody CartItem cartItem) {
		System.out.println("in add new cart item " + cartItem);
		cartItem.setProductId(productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Invalid product id")));
		cartItem.setCart(cartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Invalid card id")));
		//return productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Invalid Product ID " + productId))
		return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addNewCartItem(cartItem));
	}

	@PutMapping("/update")
	@Operation(summary = "Completely updates details of a cart item ", description = "To update details of a cart item ", tags = {
	"CartItem" })
	public ResponseEntity<?> updateCartItemDetails(@RequestBody CartItem cartItem) {

		System.out.println("in update Cart item " + cartItem);
		return ResponseEntity.ok(cartItemService.updateCartItemDetails(cartItem));

	}

	@DeleteMapping("/{cartItemId}")
	@Operation(summary = "Deletes an item in a cart ", description = "To delete a cart item  in a cart ", tags = {
	"CartItem" })
	public ResponseEntity<?> deleteCartItemDetails(@PathVariable long cartItemId) {
		System.out.println("in delete Cart item " + cartItemId);
		return ResponseEntity.ok(new ApiResponse(cartItemService.deleteCartItem(cartItemId)));

	}

}
