package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.UserRepository;
import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.UserDTO;
import com.app.jwt_utils.JwtUtils;
import com.app.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class SignInSignUpController {
	@Autowired
	private JwtUtils utils;

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private UserRepository userRepo; 
	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid AuthRequest request) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		long userId=userRepo.getUserIdByEmail(request.getEmail());
		log.info("auth token " + authToken);
		try {
			Authentication authenticatedDetails = manager.authenticate(authToken);
			log.info("auth token again " + authenticatedDetails);
			return ResponseEntity.ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticatedDetails),userId));
		} catch (BadCredentialsException e) { 
			System.out.println("err "+e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}

	}
	@PostMapping("/signup")
	public ResponseEntity<?> userRegistration(@RequestBody @Valid UserDTO user)
	{
		System.out.println("in reg user : user "+user+" roles "+user.getRoles());//{....."roles" : [ROLE_USER,...]}
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));		
	}
	
	@PostMapping("/admin/signup")
	public ResponseEntity<?> adminRegistration(@RequestBody @Valid UserDTO user)
	{
		System.out.println("in reg user : user "+user+" roles "+user.getRoles());//{....."roles" : [ROLE_USER,...]}
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));		
	}
}
