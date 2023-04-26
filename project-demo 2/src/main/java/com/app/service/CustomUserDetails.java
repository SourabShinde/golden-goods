//package com.app.service;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.app.entities.Role;
//import com.app.entities.User;
//
//public class CustomUserDetails implements UserDetails {
//
//	private User authUserDetails;
//	
//	public CustomUserDetails(User authUserDetails) {
//		super();
//		this.authUserDetails = authUserDetails;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return authUserDetails.getRoles() //Set<Role>
//				 .stream() //Stream<Role>
//				 .map(role -> new SimpleGrantedAuthority(role.getRoleName().name())) //Stream<SimpleGrantedAuthority>
//				 .collect(Collectors.toList());
//	}
//
//	
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return authUserDetails.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return authUserDetails.getFirstName();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}


package com.app.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.entities.User;

import lombok.ToString;
@ToString
public class CustomUserDetails implements UserDetails {
	private User user;

	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Meaning : This method should ret Collection(List) of granted authorities ,
		// for a specific user --which will be later stored in Auth obj
		//SimpleGrantedAuthority(String roleName)  imple  GrantedAuthority
		//UserEntity ---> Role	
		
		return user.getRoles() //Set<Role>
		 .stream() //Stream<Role>
		 .map(role -> new SimpleGrantedAuthority(role.getRoleName().name())) //Stream<SimpleGrantedAuthority>
		 .collect(Collectors.toList());		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

