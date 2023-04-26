package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.entities.Role;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//
//@Getter
//@Setter
//@ToString
@NoArgsConstructor
@Data
public class UserLoginResponse {
	
	private String firstName;
	private String lastName;
	private String email;
	private Set<String> roleNames = new HashSet<>();
	
	public UserLoginResponse(String firstName, String lastName, String email, Set<String> role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleNames = role;
		
	}
	
	
	
	

}
