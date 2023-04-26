package com.app.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.dto.OrderItemsDto;
import com.app.entities.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
	@Query("select o from OrderItems o where o.userId.id =:id")

	List<OrderItems> getOrderItemsByUserId(@Param("id") long userId);

	OrderItems save(@Valid OrderItemsDto orderItem);
	
	//orderItem --userId -- cart id 
	
}
