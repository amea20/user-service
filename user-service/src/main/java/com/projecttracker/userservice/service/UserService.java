package com.projecttracker.userservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecttracker.userservice.enums.Status;
import com.projecttracker.userservice.enums.UserType;
import com.projecttracker.userservice.model.User;
import com.projecttracker.userservice.model.UserModel;
import com.projecttracker.userservice.model.UserRequest;
import com.projecttracker.userservice.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getUsers() {
		List<UserModel> userModel = userRepository.findAll();
		if (userModel != null) {
			return userModel.stream().map(this::convertToDTO).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
	
	public User getUser(String username) {
		UserModel userModel = userRepository.findByUserName(username);
		if (userModel != null) {
		return convertToDTO(userModel);
		}
		return null;
	}
	
	public void createUser(UserRequest userRequest) {
		UserModel userModel = new UserModel();
		userModel.setUserName(generateUsername(userRequest));
		userModel.setPassword(userRequest.getPassword());
		userModel.setFirstName(userRequest.getFirstName());
		userModel.setLastName(userRequest.getLastName());
		userModel.setDob(userRequest.getDob());
		userModel.setEmailAddress(userRequest.getEmailAddress());
		userModel.setAddress1(userRequest.getAddress1());
		userModel.setAddress2(userRequest.getAddress2());
		userModel.setAddress3(userRequest.getAddress3());
		userModel.setCity(userRequest.getCity());
		userModel.setPostcode(userRequest.getPostcode());
		userModel.setCountry(userRequest.getCountry());
		userModel.setUserType(UserType.USER);
		userModel.setUserRole(null);
		userModel.setState(null);
		userModel.setStatus(Status.AWAITING_APPROVAL);
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
			user.setUserName(userModel.getUserName());
			user.setFirstName(userModel.getFirstName());
			user.setLastName(userModel.getLastName());
			user.setEmailaddress(userModel.getEmailAddress());
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
	
	protected String generateUsername(UserRequest userRequest) {
		return userRequest.getFirstName();
	}
}
