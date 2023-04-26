package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.entities.Category;
import com.app.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Category", description = "Category related functionalities.")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	public CategoryController() {
		System.out.println("In constr of " + getClass());
	}

	@GetMapping
	@Operation(summary = "Shows all Categories.", description = "To get all the listed categories.", tags = {
	"Category" })
	public ResponseEntity<?> listAllCategories() {
		System.out.println("in get all categories");
		return ResponseEntity.ok(categoryService.getAllCategories());
	}

	@PostMapping
	@Operation(summary = "Creates a new category", description = "To add a new category", tags = {
	"Category" })
	public ResponseEntity<?> addCatDetails(@RequestBody @Valid Category category) {
		System.out.println("in add category dtls " + category);
		return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);

	}

	@DeleteMapping("/{categoryId}")
	@Operation(summary = "Deletes a category ", description = "To delete a category ", tags = {
	"Category" })
	public ResponseEntity<?> deleteCategoryDetails(@PathVariable long categoryId) {
		System.out.println("in delete product " + categoryId);
		return ResponseEntity.ok(new ApiResponse(categoryService.deleteCategory(categoryId)));

	}

	@GetMapping("/{categoryId}")
	@Operation(summary = "Shows category details.", description = "To retrieve  the category details by category(id).", tags = {
	"Category" })
	public ResponseEntity<?> getCategoryDetails(@PathVariable long categoryId) {
		System.out.println("in get product " + categoryId);
		return ResponseEntity.ok(categoryService.getCategoryDetailsById(categoryId));

	}

}
