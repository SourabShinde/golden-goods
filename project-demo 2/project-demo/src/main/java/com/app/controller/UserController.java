package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.UserLoginRequest;
import com.app.dto.UserSignupRequest;
//import com.app.dto.UserLoginRequest;
//import com.app.dto.UserLoginResponse;
import com.app.entities.User;
import com.app.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
@Tag(name = "User", description = "User related functionalities.")
public class UserController {
	@Autowired
	private UserService userService;

	public UserController() {
		System.out.println("In UserController constructor");
	}

	@GetMapping
	@Operation(summary = "Shows all Listed Users.", description = "To retrieve all the users listed in.", tags = {
			"User" })
	public ResponseEntity<?> fetchAllUsers() {
		System.out.println("in get all users");
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PostMapping
	@Operation(summary = "Create a new User.", description = "To create a user.", tags = {"User" })

	public ResponseEntity<?> addUserDetails(@RequestBody @Valid UserSignupRequest user) {
		System.out.println("in add dtls " + user);

		return new ResponseEntity<>(userService.registerNewUser(user), HttpStatus.CREATED);

	}
	
	@Operation(summary = "Delete a User.", description = "To delete a user using user id.", tags = {"User" })
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUserDetails(@PathVariable @Valid long userId) {
		System.out.println("in del user " + userId);
		return ResponseEntity.ok(new ApiResponse(userService.deleteUser(userId)));

	}
	
	@Operation(summary = "Update a User.", description = "To update a user using user id.", tags = {"User" })
	@PutMapping
	public ResponseEntity<?> updateUserDetails(@RequestBody @Valid User user) {

		System.out.println("in update user " + user);
		return ResponseEntity.ok(userService.updateUserDetails(user));

	}

	@Operation(summary = "Show a User.", description = "To show a user details using user id.", tags = {"User" })
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserDetails(@PathVariable @Valid long userId) {
		System.out.println("in get user " + userId);
		return ResponseEntity.ok(userService.getUserDetails(userId));

	}

	@Operation(summary = "Sign in a User.", description = "To sign in for a user.", tags = {"User" })
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid UserLoginRequest request) {
		System.out.println("in user login " + request);
		return ResponseEntity.ok(userService.login(request));
	}
	
	@Operation(summary = "Sign up or Register a User.", description = "To sign up a user or register a user new user.", tags = {"User" })
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserSignupRequest request) {
		System.out.println("in reg user " + request);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerNewUser(request));
	}

}
