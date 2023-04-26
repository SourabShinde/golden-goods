package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CartItemRepository;
import com.app.entities.CartItem;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepo;

	@Override
	public List<CartItem> getCartItemById(long cartId) {
		
		System.out.println("in cartItem service implementation "+getClass() );
		return cartItemRepo.getCartItemById(cartId);
	}
	@Override
	public CartItem addNewCartItem(CartItem cartItem) {
		System.out.println("in add cart item method "+getClass());
		return cartItemRepo.save(cartItem);
	}
	@Override
	public CartItem updateCartItemDetails(CartItem cartItem) {
		System.out.println("in update cart item "+getClass());
		return cartItemRepo.save(cartItem);
	}
	@Override
	public String deleteCartItem(long cartItemId) {
		cartItemRepo.deleteById(cartItemId);
		return "Cart item deleted for cart Item id " + cartItemId;
	}
//	@Override
//	public CartItem getCartItemDetailsById(long cartItemId) {
//		System.out.println("in cart item details"+ getClass());
//		return cartItemRepo.findById(cartItemId);
//	}
}