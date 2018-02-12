package com.projecttracker.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecttracker.userservice.model.CountryModel;

public interface CountryRepository extends JpaRepository<CountryModel,Integer> {

	public List<CountryModel> findAllByOrderByCountryValue();
}
