package com.education.lending.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.education.lending.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**User details service for authentication with spring
 * 
 * @author Suresh Injeti
 *
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	log.info("=====loadUserByUsername====="+username);
    	Optional<com.education.lending.entity.User> userObj = userRepository.findByLoginid(username);
		if(userObj.isEmpty()) {
			log.warn("Recod not found");
			throw new UsernameNotFoundException("User not found");
		}
		com.education.lending.entity.User user = userObj.get();
		
    	log.info("loadUserByUsername ==="+username);
       if (user != null) {
            return new User(user.getLoginId(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().name())));
        }else {
        	throw new UsernameNotFoundException("User not found");
        }
    }

}
