package com.education.lending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.lending.entity.User;
import com.education.lending.entity.enums.Role;
import com.education.lending.pojo.AuthRequest;
import com.education.lending.pojo.AuthResponse;
import com.education.lending.pojo.SignupRequest;
import com.education.lending.security.CustomUserDetailsService;
import com.education.lending.security.JwtUtil;
import com.education.lending.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**Authentication API controller
 * 
 * @author Suresh Injeti
 *
 */

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		log.info("Authentication controller login::====" + request.getUserName());
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
			log.info("Authetnication success");
			final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
			final String token = jwtUtil.generateToken(userDetails);
			log.info("Token ====" + token);
			return ResponseEntity.ok(new AuthResponse(token));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Authentication failed");
		}
    }
    
	@GetMapping("/logout")
	public ResponseEntity<?> logoutPage(HttpServletRequest request, HttpServletResponse response) {
		log.info("Entered into logoutPage method");
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null) {
				new SecurityContextLogoutHandler().logout(request, response, authentication);
			} else {
				log.warn("Do not identify current loggedin user info");
			}
			log.info("Exit from logoutPage method");
			return ResponseEntity.ok("User logout successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Logout failed");
		}
	}
	
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
    	try {
    		User user = mapToUser(request);
    		userService.createUser(user);
    		return ResponseEntity.ok().body(user.toString());
    	} catch (Exception e) {
			return ResponseEntity.badRequest().body("User signup failed");
		}
    }
    
    private User mapToUser(SignupRequest signUpRequest) {
    	User user = new User();
    	user.setEmail(signUpRequest.getEmail());
    	user.setLoginId(signUpRequest.getLoginId());
    	user.setMobile(signUpRequest.getMobile());
    	user.setPassword(signUpRequest.getPassword());
    	user.setName(signUpRequest.getName());
    	if(signUpRequest.getRole() !=null && signUpRequest.getRole().equalsIgnoreCase(Role.STAFF.name()))
    		user.setRole(Role.STAFF);
    	else
    		user.setRole(Role.USER);
    	return user;
    }
}
