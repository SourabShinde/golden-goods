package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("select p from Product p where p.category.id =:id")

	List<Product> getProductsByCategoryId(@Param("id") long categoryId);
}
