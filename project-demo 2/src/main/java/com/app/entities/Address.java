package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address extends BaseEntity{
	/*
	 * address_id line1 line2 city state pincode
	 */
	
	@Column(length = 50, name = "line1")
	private String line1;
	@Column(length = 50, name = "line2")
	private String line2;
	@Column(length=20, name="city")
	private String city;
	@Column(length=20, name="state")
	private String state;
	@Column(length=6, name="pincode")
	private long pinCode;
	@ManyToOne //Multiple addresses for one user //From address to user
	@JoinColumn(name = "user_id", nullable = false) //Child side, Owning side as it contains foreign key
	private User user; //inverse side
	
	
}
