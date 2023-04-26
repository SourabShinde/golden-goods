package com.app.service;

import java.util.List;

import com.app.entities.CartItem;

public interface CartItemService {


	List<CartItem> getCartItemById(long cartId);

	CartItem addNewCartItem(CartItem cartItem);

	CartItem updateCartItemDetails(CartItem cartItem);

	String deleteCartItem(long cartItemId);

//    CartItem getCartItemDetailsById(long cartItemId);

}
