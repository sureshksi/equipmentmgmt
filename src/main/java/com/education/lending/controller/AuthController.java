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

import com.education.lending.pojo.AuthRequest;
import com.education.lending.pojo.AuthResponse;
import com.education.lending.security.CustomUserDetailsService;
import com.education.lending.security.JwtUtil;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		log.info("Authentication controller login::===="+request.getUserName());
        
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        log.info("Authetnication success");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        final String token = jwtUtil.generateToken(userDetails);
        log.info("Token ===="+token);
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
	@GetMapping("/logout")
	public ResponseEntity<?> logoutPage(HttpServletRequest request, HttpServletResponse response) {
		log.info("Entered into logoutPage method");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			//UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}else {
			log.warn("Do not identify current loggedin user info");
		}
		log.info("Exit from logoutPage method");
		return ResponseEntity.ok("User logout successfully");
	}
	
}
