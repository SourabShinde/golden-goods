package com.app.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.CategoryRepository;
import com.app.dto.ApiResponse;
import com.app.entities.Product;
import com.app.service.CategoryService;
import com.app.service.ImageHandlingService;
import com.app.service.ProductService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
@Tag(name = "Product", description = "Product related functionalities.")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ImageHandlingService imageHandlingService;

	public ProductController() {
		System.out.println("in constructor of Product " + getClass());
	}

	@GetMapping("/{categoryId}")
	@Operation(summary = "Shows all Products in a Category.", description = "To retrieve all the products listed in a category(id).", tags = {
	"Product" })
	public ResponseEntity<?> getProductsByCategoryId(@PathVariable @Valid long categoryId) {
		System.out.println("in get all products");
		return ResponseEntity.ok(productService.getProductByCategory(categoryId));
	}

	@GetMapping("/product/{productId}")
	@Operation(summary = "Shows product details using product id.", description = "To retrieve  the products details by product(id).", tags = {
	"Product" })
	public ResponseEntity<?> getProductDetails(@PathVariable @Valid long productId) {
		System.out.println("in get product " + productId);
		return ResponseEntity.ok(productService.getProductDetailsById(productId));

	}

	@PostMapping("/{categoryId}")
	@Operation(summary = "Adds product details under a category", description = "To add a new product under a category.", tags = {
	"Product" })
	public ResponseEntity<?> addProductDetails(@PathVariable long categoryId, @RequestBody @Valid Product product) {
		//System.out.println(categoryId);
		System.out.println(product);
		product.setCategory(categoryService.getCategoryDetailsById(categoryId));     
		System.out.println("in add dtls " + product);
		
		return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);

	}

	@PutMapping
	@Operation(summary = "Completely updates details of a product ", description = "To update details of a product ", tags = {
	"Product" })
	public ResponseEntity<?> updateProductDetails(@RequestBody @Valid Product product) {

		System.out.println("in update product " + product);
		return ResponseEntity.ok(productService.updateProductDetails(product));

	}

	@DeleteMapping("/{productId}")
	@Operation(summary = "Deletes a product by the given product Id ", description = "To delete a product by specified productid ", tags = {
	"Product" })
	public ResponseEntity<?> deleteProductDetails(@PathVariable @Valid long productId) {
		System.out.println("in delete product " + productId);
		return ResponseEntity.ok(new ApiResponse(productService.deleteProduct(productId)));

	}
	
	@PostMapping("/{productId}/images")
	public ResponseEntity<?> uploadProductImage(@PathVariable long productId,@RequestParam MultipartFile imageFile) throws IOException{
		System.out.println("In uploadImage for product with ID"+productId);
		System.out.println("Image type"+imageFile.getContentType());
		System.out.println("Image Size"+imageFile.getSize());
		return ResponseEntity.ok(imageHandlingService.saveImage(productId,imageFile));
		
	}
	@GetMapping(value="/{productId}/images",produces = {MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<?> downloadImage(@PathVariable long productId) throws IOException{
		System.out.println("in image download"+productId);
		return ResponseEntity.ok(imageHandlingService.restoreImage(productId));
	}

}
