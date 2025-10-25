package com.education.lending.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.lending.entity.User;
import com.education.lending.repository.UserRepository;
import com.education.lending.service.UserService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * User service implemented methods
 * 
 * @author Suresh Injeti
 *
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User getUserByLogin(String lodinId) {

		Optional<User> userObj = userRepository.findByLoginid(lodinId);
		if (userObj.isEmpty()) {
			log.warn("User not found");
			return null;
		} else {
			log.info("User available");
			return userObj.get();
		}

	}

	@Override
	public User getUserByEmail(String email) {

		Optional<User> userObj = userRepository.findByEmail(email);
		if (userObj.isEmpty()) {
			return null;
		} else {
			return userObj.get();
		}
	}

	@Transactional
	@Override
	public User updateUser(User user) {
		userRepository.save(user);
		log.info("User updated successfully");
		return user;
	}

	@Transactional
	@Override
	public User createUser(User user) {
		userRepository.save(user);
		log.info("User created successfully");
		return user;
	}

}
