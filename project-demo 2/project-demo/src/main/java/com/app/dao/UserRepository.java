package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dto.UserSignupRequest;
import com.app.entities.CartItem;
import com.app.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User,Long>{
	
	//User authentication method
	Optional <User> findByEmailAndPassword(String email,String password);
	
	//@Query("select c from CartItem c where c.Cart.id=(select c.id from Cart c where c.userId=:uid)")
	@Query("select c from CartItem c inner join c.cart b where b.id=(select c.id from Cart c where c.userId.id=:uid)")
	List<CartItem> getCartItemById(@Param("uid") long userId);

	UserSignupRequest save(UserSignupRequest user);
	

}
