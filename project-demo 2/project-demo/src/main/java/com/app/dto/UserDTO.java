package com.app.dto;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.app.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
@Data
public class UserDTO {
	@NotBlank(message = "First name should not be blank")
	private String firstName;
	@NotBlank(message = "Last name should not be blank")
	private String lastName;
	@NotBlank(message = "Email is compulsary")
	@Email(message = "Invalid email pattern")
	private String email;
	@Length(min=9, max=10,message="Please enter a valid 10 digit mobile number")
	private String mobileNumber;
	@Pattern(regexp = "((?=.*\\d)(?=.,*[a-z])(?=.*[#@$*]).{6,20})",message = "Password should contain digits, alphabets, character and lenght between 6-20")
	private String password;
	@DateTimeFormat(pattern = "dd-MMM-YYYY")
	private Date registeredDate;
	private Set<Role> roles = new HashSet<>();
}
