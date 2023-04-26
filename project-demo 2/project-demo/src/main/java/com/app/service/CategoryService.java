package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.entities.Category;

public interface CategoryService {
	
	List<Category> getAllCategories();

	Category addCategory( Category category);

	String deleteCategory(long categoryId);

	Category getCategoryDetailsById(long categoryId);

}
