package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ProductRepository;
import com.app.entities.Product;
import com.app.exception.ResourceNotFoundException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public List<Product> getProductByCategory(long categoryId) {

		return productRepo.getProductsByCategoryId(categoryId);
	}

	@Override
	public String deleteProduct(long productId) {
		productRepo.deleteById(productId);
		return "Product details deleted for product id " + productId;
	}

	@Override
	public Product getProductDetailsById(long productId) {
		// TODO Auto-generated method stub
		return productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Invalid Product ID " + productId));
	}

	@Override
	public Product addProduct(Product product) {
		System.out.println("in add product method "+getClass());
		return productRepo.save(product);
	}

	@Override
	public Product updateProductDetails(Product product) {
		if (productRepo.existsById(product.getId()))
			return productRepo.save(product);//update
		throw new ResourceNotFoundException("Invalid Product ID : Updation Failed !" + product.getId());
	}
}
