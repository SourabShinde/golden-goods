package com.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="cart_items")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartItem extends BaseEntity{
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name="cart_cart_items",joinColumns =@JoinColumn(name="cart_item_id"),inverseJoinColumns = @JoinColumn(name="cart_id"))
//	private Set<Cart> carts = new HashSet<>();
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "cart_cart_item", joinColumns = @JoinColumn(name = "cart_item_id"), inverseJoinColumns = @JoinColumn(name = "cart_id"))
//	private Set<Cart> carts = new HashSet<Cart>();
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name="cart_item_products",joinColumns =@JoinColumn(name="cart_item_id"),inverseJoinColumns = @JoinColumn(name="product_id"))
//	private Set<Product> products = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Product> productId = new HashSet<Product>();
	
	private int quantity;
	private long total;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
}
