package com.projecttracker.userservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.projecttracker.userservice.enums.Status.*;
import static com.projecttracker.userservice.enums.UserType.*;
import com.projecttracker.userservice.model.User;
import com.projecttracker.userservice.model.UserModel;
import com.projecttracker.userservice.model.UserRequest;
import com.projecttracker.userservice.repository.UserRepository;
import com.projecttracker.userservice.validation.UserValidator;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserValidator userValidator;
	
	@Autowired
	public UserService(UserRepository userRepository,UserValidator userValidator) {
		this.userRepository = userRepository;
		this.userValidator = userValidator;
	}
	
	public List<User> getUsers() {
		List<UserModel> userModel = userRepository.findAll();
		if (userModel != null) {
			return userModel.stream().map(this::convertToDTO).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
	
	public User getUser(String username) {
		UserModel userModel = userRepository.findByUsername(username);
		if (userModel != null) {
		return convertToDTO(userModel);
		}
		return null;
	}
	
	public void createUser(UserRequest userRequest) {
		UserModel userModel = new UserModel();
		userModel.setUsername(generateUsername(userRequest));
		userModel.setPassword(userRequest.getPassword());
		userModel.setFirstname(userRequest.getFirstname());
		userModel.setLastname(userRequest.getLastname());
		userModel.setDob(userRequest.getDob());
		userModel.setEmailaddress(userRequest.getEmailaddress());
		userModel.setAddress1(userRequest.getAddress1());
		userModel.setAddress2(userRequest.getAddress2());
		userModel.setAddress3(userRequest.getAddress3());
		userModel.setCity(userRequest.getCity());
		userModel.setPostcode(userRequest.getPostcode());
		userModel.setCountry(userRequest.getCountry());
		userModel.setUserType(USER);
		userModel.setUserRole(null);
		userModel.setStatus(AWAITING_APPROVAL);
		userModel.setDateCreated(LocalDateTime.now());
		userModel.setDateModified(LocalDateTime.now());
		userModel.setLocked(false);
		userModel.setDisabled(false);
		userModel.setLoginAttempts(0);
		userModel.setLoggedinDatetime(null);
		userModel.setLoggedoutDatetime(null);
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user.getUserId());
	}
	public User convertToDTO(UserModel userModel) {
		if (userModel != null) {
			User user = new User();
			user.setUserId(userModel.getUserId());
			user.setUsername(userModel.getUsername());
			user.setFirstname(userModel.getFirstname());
			user.setLastname(userModel.getLastname());
			user.setEmailaddress(userModel.getEmailaddress());
			user.setDob(userModel.getDob());
			user.setUserRole(userModel.getUserRole().name());
			user.setUserType(userModel.getUserType().name());
			user.setAddress1(userModel.getAddress1());
			user.setAddress2(userModel.getAddress2());
			user.setAddress3(userModel.getAddress3());
			user.setCity(userModel.getCity());
			user.setPostcode(userModel.getPostcode());
			user.setCountry(userModel.getCountry());
			user.setCity(userModel.getCity());
			user.setCountry(userModel.getCountry());
			user.setPostcode(userModel.getPostcode());
			return user;
		}
		return null;
	}
	
	public List<String> checkUserExists(String username, String password) {
		List<String> validationErrors = new ArrayList<>();
		User user = getUser(username);
		if (user == null) { validationErrors.add("User does not exist"); return validationErrors; }
		if (!password.equals(user.getPassword())) { validationErrors.add("Password is incorrect"); return validationErrors; }
		validationErrors = userValidator.validateStatus(user);
		return validationErrors;
	}
	
	protected String generateUsername(UserRequest userRequest) {
		return userRequest.getFirstname();
	}
}
