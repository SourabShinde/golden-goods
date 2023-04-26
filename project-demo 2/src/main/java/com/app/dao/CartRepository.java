package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Cart;
import com.app.entities.Order;
import com.app.entities.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
	@Query("select c.userId  from Cart c where c.id=:id")
	User findUserId( @Param("id") long cartId);
	
	@Query("select c.id from Cart c where c.userId.id=:id")
	long getByUserId(@Param("id") long userId);
	
	@Query(value="insert into cart (id,user_id) values(:userId,:userId)",nativeQuery=true)
	void setCardId(@Param("userId") long cartId,@Param("userId") long userId);
}
