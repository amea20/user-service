package com.projecttracker.userservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
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
		
	public Map<String,String> getCountries() {
		Map<String,String> countriesMap = new TreeMap<>();
		//SQLQuery query = session.createSQLQuery("SELECT country_name,country_code FROM countries");
//		List<Object[]> countries = query.list();
//		for (Object[] country:countries) {
//			countriesMap.put(country[0].toString(), country[1].toString());
//		}
		
		return countriesMap;
	}
	
	protected String generateUsername(UserRequest userRequest) {
		String username = userRequest.getFirstname().toLowerCase().substring(0, 1) + userRequest.getLastname().toLowerCase();
	//	Query query	= session.createQuery("from User where username LIKE :username order by username DESC");
	//	query.setParameter("username", username + "%");
	//	query.setMaxResults(1);
	//	if (query.list().size() != 0) {
	//		User user = (User) query.list().get(0);
	//		if (user.getUsername().equals(username)) {
	//			return username + "1";
	//		}
		int sequence = Integer.parseInt(userRequest.getUsername().substring(userRequest.getUsername().length()));
		username = userRequest.getFirstname().toLowerCase().charAt(0) + userRequest.getLastname().toLowerCase() + ++sequence;
//		}
		return username;
	}
	
	public String generateTempPass() {
			Random random = new Random();
			String upperCase ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
			String numbers = "0123456789";
			String specialChars = "~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
			char [] tempPass = new char[11];
			for (int i=0;i<tempPass.length;i++) {
			switch(random.nextInt(3)) {
			case(0): tempPass[i] = upperCase.charAt(random.nextInt(upperCase.length()));
			break;
	        case(1): tempPass[i] = lowerCase.charAt(random.nextInt(lowerCase.length()));
			break;
			case(2): tempPass[i] = numbers.charAt(random.nextInt(numbers.length()));
			break;
			case(3): tempPass[i] = specialChars.charAt(random.nextInt(specialChars.length()));
			break;
			default:
			}
	        }
	    	return String.valueOf(tempPass);
	}
}