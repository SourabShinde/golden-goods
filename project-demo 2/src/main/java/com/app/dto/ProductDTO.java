package com.app.dto;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.app.entities.Category;
import com.app.entities.ProductStatus;

import lombok.Data;
@Data
public class ProductDTO {
	@NotBlank
	@Length(max = 20, message = "Product Name lenght should be less than 20 characters.")
	private String productName;
//	@NotBlank(message = "Product image must be provided.")
	private String productImage;
	@Min(value = 0, message = "Marked price should not be less than 0")
	private long markedPrice;
	@Min(value = 0, message = "Selling price should not be less than 0")
	private long sellingPrice;
	@Min(value = 0, message = "Quantity should not be negative")
	private int quantity;
	private ProductStatus status;
	@Length(min = 10, max = 1000, message = "Description lenght should between 10-1000 characters.")
	private String description;

}
