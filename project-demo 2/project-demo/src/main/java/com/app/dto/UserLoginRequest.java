package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//
//@Getter
//@Setter
//@ToString
@Data
public class UserLoginRequest {
	@NotBlank(message="Email is required")
	private String email;
	@NotBlank(message="Password is required")
	private String password;
	
	
	

}
