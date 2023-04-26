package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CategoryRepository;
import com.app.entities.Category;
import com.app.exception.ResourceNotFoundException;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public List<Category> getAllCategories() {
		System.out.println("in category service implementation "+getClass() );
		return categoryRepo.findAll();
	}

	@Override
	public Category addCategory(Category category) {
		System.out.println("in add category method "+getClass());
		return categoryRepo.save(category);
	}

	@Override
	public String deleteCategory(long categoryId) {
		categoryRepo.deleteById(categoryId);
		return "Category details deleted for category id " + categoryId;
	}

	@Override
	public Category getCategoryDetailsById(long categoryId) {
		return categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Invalid Product ID " + categoryId));
	}

}
