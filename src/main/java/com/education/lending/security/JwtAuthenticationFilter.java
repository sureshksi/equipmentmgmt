package com.education.lending.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.education.lending.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**JWT filter for authenticate or use token
 * 
 * @author Suresh Injeti
 *
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = jwtUtil.getJwtFromRequest(request);
		if (StringUtils.hasText(jwt) && jwtUtil.validateToken(jwt)) {
			String userName = jwtUtil.extractUsername(jwt);
			List<GrantedAuthority> roles = jwtUtil.extractRoles(jwt);

			log.info("JWT token is valid");
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, roles);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		filterChain.doFilter(request, response);
	}
     
}

