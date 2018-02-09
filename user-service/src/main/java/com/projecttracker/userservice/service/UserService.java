package com.projecttracker.userservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecttracker.userservice.model.User;
import com.projecttracker.userservice.model.UserModel;
import com.projecttracker.userservice.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getUsers() {
		List<UserModel> user = userRepository.findAll();
		if (user != null) {
			return user.stream().map(u -> convertToDTO(u)).collect(Collectors.toList());
		}
		return null;
	}
	
	public User getUser(String username) {
		UserModel userModel = userRepository.findByUserName(username);
		if (userModel != null) {
			User user = convertToDTO(userModel);
		return user;
		}
		return null;
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
}
