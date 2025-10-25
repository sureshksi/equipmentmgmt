package com.education.lending.service;

import com.education.lending.entity.User;

/**User service defined methods
 * 
 * @author Suresh Injeti
 *
 */
public interface UserService {

	public User getUserByLogin(String lodinId);
	public User getUserByEmail(String email);
	public User updateUser(User user);
	public User createUser(User user);
	
}
