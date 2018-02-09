package com.projecttracker.userservice.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.projecttracker.userservice.model.UserRequest;

@Component
public class UserValidator {

	List<String> validationErrors = new ArrayList<>();
	
	public List<String> validate(UserRequest userRequest) {
		validationErrors = validateFields(userRequest);
		if (validationErrors.isEmpty()) return new ArrayList<>();
		return validationErrors;
	}
	
	public List<String> validateFields(UserRequest userRequest) {
		if (StringUtils.isEmptyOrWhitespaceOnly(userRequest.getFirstName())) validationErrors.add("First name is mandatory.");
		if (StringUtils.isEmptyOrWhitespaceOnly(userRequest.getLastName())) validationErrors.add("Last name is mandatory.");
		if (StringUtils.isEmptyOrWhitespaceOnly(userRequest.getEmailAddress())) validationErrors.add("Email address is mandatory.");
		if (StringUtils.isEmptyOrWhitespaceOnly(userRequest.getPassword())) validationErrors.add("Password is mandatory.");
		if (StringUtils.isEmptyOrWhitespaceOnly(userRequest.getConfirmPassword())) validationErrors.add("Confirm password is mandatory.");
		if (StringUtils.isEmptyOrWhitespaceOnly(userRequest.getAddress1())) validationErrors.add("Address line 1 is mandatory.");
		if (StringUtils.isEmptyOrWhitespaceOnly(userRequest.getCity())) validationErrors.add("City is mandatory.");
		if (StringUtils.isEmptyOrWhitespaceOnly(userRequest.getPostcode())) validationErrors.add("Postcode is mandatory.");
		if (StringUtils.isEmptyOrWhitespaceOnly(userRequest.getCountry())) validationErrors.add("Country is mandatory.");
		if ((userRequest.getDob() == null)) validationErrors.add("Date of birth is mandatory.");
		return validationErrors;
	}
}
