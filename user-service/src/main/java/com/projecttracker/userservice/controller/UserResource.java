package com.projecttracker.userservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projecttracker.userservice.model.User;
import com.projecttracker.userservice.model.UserRequest;
import com.projecttracker.userservice.service.UserService;
import com.projecttracker.userservice.validation.UserValidator;

@RestController
public class UserResource {

	private final UserService userService;
	private final UserValidator userValidator;
	
	@Autowired
	public UserResource(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
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
	
	@PostMapping("/checkLogin")
	public ResponseEntity checkUser(@Valid @RequestBody UserRequest userRequest) {
		List<String> validationErrors = userService.checkUserExists(userRequest.getUsername(),userRequest.getPassword());
		if (validationErrors.isEmpty()) return new ResponseEntity<>(userRequest,HttpStatus.OK);
		return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/users")
	public ResponseEntity createUser(@Valid @RequestBody UserRequest userRequest) {
		List<String> validationErrors = userValidator.validate(userRequest);
		if (validationErrors.isEmpty()) {
		userService.createUser(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(userRequest);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
	}
	
	@DeleteMapping("/users")
	public ResponseEntity deleteUser(String username) {
		User user = userService.getUser(username);
		if (user != null) { userService.deleteUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(user);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user does not exist");
	}
}