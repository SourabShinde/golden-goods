package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.dto.OrderDto;
import com.app.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Query("select o from Order o where o.userId.id =:id")

	List<Order> getOrdersByUserId(@Param("id") long userId);

}
