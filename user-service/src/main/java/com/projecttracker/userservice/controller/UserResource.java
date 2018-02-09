package com.projecttracker.userservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.projecttracker.userservice.model.User;
import com.projecttracker.userservice.service.UserService;

@RestController
public class UserResource {

	private final UserService userService;
	
	@Autowired
	public UserResource(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.getUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@GetMapping("/users/{username}")
	public ResponseEntity getUser(@PathVariable("username") String username) {
		User user = userService.getUser(username);
		
		if (user!= null) {
		return new ResponseEntity<>(user,HttpStatus.OK);
		}
		return new ResponseEntity<>(String.format("User %s does not exist", username),HttpStatus.NOT_FOUND);
	}
}
