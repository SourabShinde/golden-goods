package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.entities.User;


@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepo;
	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		User authenticatedUser=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Invalid email"));
//		
//		return new CustomUserDetails(authenticatedUser);
//	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("in load by user nm " + email);
		// invoke dao's method to load user details from db by username(ie. actaully an
		// email)
		User user = userRepo.
				findByEmail(email).
				orElseThrow(() -> new UsernameNotFoundException("Invalid Email ID "));
		System.out.println("lifted user dtls from db "+user);
		return new CustomUserDetails(user);
	}

}
