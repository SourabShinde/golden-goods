package com.app.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.app.dto.ApiResponse;
import com.app.dto.UserLoginRequest;
import com.app.dto.UserLoginResponse;
import com.app.dto.UserSignupRequest;
import com.app.entities.CartItem;
import com.app.entities.User;

public interface UserService {

//	UserSignupRequest addUser(UserSignupRequest user);

	List<User> getAllUsers();

	String deleteUser(long userId);

	User updateUserDetails(User user);

	User getUserDetails(long userId);

	List<CartItem> getCartItemsByUserId(@Param("id") long userId);

	ApiResponse registerNewUser(UserSignupRequest userDto);

	UserLoginResponse login(UserLoginRequest request);


}
