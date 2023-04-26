package com.app.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity{
	@ManyToOne
	@JoinColumn(name="category_id",nullable=false)
	private Category category;
	@Column(length = 20)
	private String productName;
	private String productImage;
	private long markedPrice;
	private long sellingPrice;
	private int quantity;
	private ProductStatus status;
	private String description;
}
