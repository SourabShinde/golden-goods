package com.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItems extends BaseEntity{
//	@ManyToMany
//	@JoinTable(name="order_order_items",joinColumns = @JoinColumn(name="order_id"),inverseJoinColumns = @JoinColumn(name="order_items_id"))
//	private Set<Order> orders=new HashSet<>();
//	@ManyToMany
//	@JoinTable(name="product_order_items",joinColumns = @JoinColumn(name="product_id"),inverseJoinColumns = @JoinColumn(name="order_items_id"))
//	private Set<Product> products=new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productId;
	private int quantity;
	private long totalBill;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;
	
	
}
