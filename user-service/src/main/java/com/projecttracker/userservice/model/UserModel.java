package com.projecttracker.userservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.projecttracker.userservice.enums.Status;
import com.projecttracker.userservice.enums.UserRole;
import com.projecttracker.userservice.enums.UserType;


@Entity
@Table(name="Users")
public class UserModel {

	@Id
	@Column(name="USERID")
	private Integer userId;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="FIRSTNAME")
	private String firstname;
	
	@Column(name="SURNAME")
	private String lastname;
	
	@Column(name="DOB")
	private LocalDateTime dob;
	
	@Column(name="EMAIL_ADDRESS")
	private String emailaddress;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Column(name="ADDRESS_1")
	private String address1;
	
	@Column(name="ADDRESS_2")
	private String address2;
	
	@Column(name="ADDRESS_3")
	private String address3;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="POSTCODE")
	private String postcode;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Enumerated(EnumType.STRING)
	@Column(name="USER_TYPE")
	private UserType userType;
	
	@Enumerated(EnumType.STRING)
	@Column(name="USER_ROLE")
	private UserRole userRole;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status;
	
	@Column(name="DATE_CREATED")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDateTime dateCreated;
	
	@Column(name="DATE_MODIFIED")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDateTime dateModified;
	
	@Column(name="LOCKED")
	private boolean locked;
	
	@Column(name="DISABLED")
	private boolean disabled;
	
	@Column(name="NUM_ATTEMPTS")
	private int loginAttempts;
	
	@Column(name="LOGGEDIN_DATETIME")
	private LocalDateTime loggedinDatetime;
	
	@Column(name="LOGGEDOUT_DATETIME")
	private LocalDateTime loggedoutDatetime;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public LocalDateTime getDob() {
		return dob;
	}
	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public LocalDateTime getDateModified() {
		return dateModified;
	}
	public void setDateModified(LocalDateTime dateModified) {
		this.dateModified = dateModified;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public int getLoginAttempts() {
		return loginAttempts;
	}
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}
	public LocalDateTime getLoggedinDatetime() {
		return loggedinDatetime;
	}
	public void setLoggedinDatetime(LocalDateTime loggedinDatetime) {
		this.loggedinDatetime = loggedinDatetime;
	}
	public LocalDateTime getLoggedoutDatetime() {
		return loggedoutDatetime;
	}
	public void setLoggedoutDatetime(LocalDateTime loggedoutDatetime) {
		this.loggedoutDatetime = loggedoutDatetime;
	}
}