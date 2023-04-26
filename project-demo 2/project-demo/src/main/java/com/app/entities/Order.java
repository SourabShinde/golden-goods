package com.app.entities;

import java.sql.Date;

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
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity{
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User userId;
	private long totalPrice;
	private Date creationDate;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
}
