package com.projecttracker.userservice.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="Users")
public class UserModel {

	@Id
	@Column(name="USERID")
	private Integer userId;
	
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="SURNAME")
	private String lastName;
	
	@Column(name="DOB")
	private Date dob;
	
	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;
	
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
	
	//approved or rejected
	@Column(name="STATE")
	private String state;
	
	//awaiting_approval, reset password, available, disabled, locked
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DATE_CREATED")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateCreated;
	
	@Column(name="DATE_MODIFIED")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateModified;
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
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
