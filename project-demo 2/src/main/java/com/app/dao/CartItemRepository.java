package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.CartItem;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
//	CartItem findById(long cartItemId);
//	
//	@Query("select c from CartItem c where c.cart.id =:id")
//
//	List<CartItem> getCartItemById(@Param("id") long cartId);
//	
//	@Query("select c from CartItem c where c.cart.id=:id")
//	List<CartItem> findByCartId(@Param("id") long cartId);

}
