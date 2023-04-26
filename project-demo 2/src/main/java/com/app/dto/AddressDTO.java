package com.app.dto;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
//@AllArgsConstructor
@Data
public class AddressDTO {
	
	private String adrLine1;

	private String adrLine2;

	private String city;

	private String state;

	private String country;
	@Length(min = 6, message = "Should be 6 digits")
	private String zipCode;

//	public AddressDTO(String addressLine1, String addressLine2, String city, String state, String co
}
