package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.validator.constraints.ParameterScriptAssert;

import com.app.entities.Product;

public interface ProductService {
	
	List<Product> getProductByCategory (long categoryId);

	String deleteProduct(long productId);

	Product getProductDetailsById(long productId);

	Product addProduct(Product product);

	Product updateProductDetails(Product product);	
}
