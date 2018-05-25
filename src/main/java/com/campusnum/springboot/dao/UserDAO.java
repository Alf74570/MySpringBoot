package com.campusnum.springboot.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.campusnum.springboot.model.User;

public interface UserDAO extends CrudRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);


}
