package com.projecttracker.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projecttracker.userservice.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	public UserModel findByUsername(String username);
	
	public UserModel findTopLikeUsernameByOrderByUsernameDesc(String username);
}
