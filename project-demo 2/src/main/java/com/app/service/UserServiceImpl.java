package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.app.dao.CartItemRepository;
import com.app.dao.RoleRepository;
import com.app.dao.UserRepository;
import com.app.dto.ApiResponse;
import com.app.dto.UserDTO;
import com.app.dto.UserLoginRequest;
import com.app.dto.UserLoginResponse;
import com.app.dto.UserRegResponse;
import com.app.dto.UserSignupRequest;
import com.app.entities.CartItem;
import com.app.entities.User;
import com.app.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartItemRepository cartItemRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserRegResponse registerUser(UserDTO user) {
		// Objective : 1 rec inserted in users table n insert n recs in link table
		// user_roles
		// 1. Map dto --> entity
		User userEntity = mapper.map(user, User.class);
		// 2. Map Set<UserRole : enum> ---> Set<Role :entity> n assign it to the
		// transient user entity
		userEntity.setRoles(roleRepo.findByRoleNameIn(user.getRoles()));
		// 3. encode pwd
		userEntity.setPassword(encoder.encode(user.getPassword()));
		// 4 : Save user details
		User persistentUser = userRepo.save(userEntity);
		return new UserRegResponse("User registered successfully with ID " + persistentUser.getId());
	}
//
//	@Override
//	public UserLoginResponse login(UserLoginRequest request) throws ResourceNotFoundException {
//		User user = userRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
//						.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !"));
//		
//		UserLoginResponse resp = mapper.map(user, UserLoginResponse.class);
//		
//		user.getRoles(); // Set<Role>
//		return resp;
//	}



//	@Override
//	public UserSignupRequest addUser(UserSignupRequest user) {
//		System.out.println("in add user method "+getClass());
//		return userRepo.save(user);
//	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}
	
	@Override
	public String deleteUser(long userId) {
		userRepo.deleteById(userId);
		return "User details deleted for user id " + userId;
	}
	
	@Override
	public User updateUserDetails(User user) {
		if (userRepo.existsById(user.getId()))
			return userRepo.save(user);//update
		throw new ResourceNotFoundException("Invalid User ID : Updation Failed !" + user.getId());
	}
	
	@Override
	public User getUserDetails(long userId) {
		// TODO Auto-generated method stub
		return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid User ID " + userId));
	}


	@Override
	public List<CartItem> getCartItemsByUserId(long userId) {
		return userRepo.getCartItemById(userId);
	}

	@Override
	public ApiResponse registerNewUser(UserSignupRequest userDto) {
		// map user signup dto --> user entity for persistence (model mapper will retain
		// default values for the fields which are not matching between dto n entity)
		User transientUser = mapper.map(userDto, User.class);
		log.info("user dto {} transient user {} ", userDto, transientUser);
		transientUser.setRoles(roleRepo.findByRoleNameIn(userDto.getUserRoles()));
		log.info("roles {}",transientUser.getRoles());
		User persistentUser = userRepo.save(transientUser);
		return new ApiResponse("User registered with ID " + persistentUser.getId() + " successfully!");
	}
	
	@Override
	public UserLoginResponse login(UserLoginRequest request) {
		User user = userRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		UserLoginResponse resp = mapper.map(user, UserLoginResponse.class);
		// map Set<Role> ---> Set<String> 
		user.getRoles() // Set<Role>
				.forEach(role -> resp.getRoleNames().add(role.getRoleName().name()));
		return resp;
		
	}

}
