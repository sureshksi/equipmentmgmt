package com.education.lending.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.education.lending.entity.User;

/**Repository for users
 * 
 * @author Suresh Injeti
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.loginId = :loginid")
	Optional<User> findByLoginid(String loginid);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> findByEmail(String email);
	
}
