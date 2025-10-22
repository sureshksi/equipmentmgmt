package com.education.lending.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.education.lending.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**JWT utility used to generate, validate tokens
 * 
 * @author Suresh Injeti
 *
 */
@Component
@Slf4j
public class JwtUtil {
	private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	@Value("${app.jwtExpirationInMs}")
	private long validityMs = 1000 * 60 * 60 * 8; // 8 hours

	public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        claims.put("roles", roles.stream().map(GrantedAuthority::getAuthority).toList());

        return Jwts.builder()
            .setClaims(claims)//Roles
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + validityMs))
            .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
            .compact();
    }
	
	public String generateToken(User user) {
		long now = System.currentTimeMillis();
		return Jwts.builder()
				.setSubject(user.getName())
				.claim("role", user.getRole().name())
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + validityMs)).signWith(SECRET_KEY).compact();
	}
	public String extractUsername(String token) {
		try {
			return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
		} catch (JwtException e) {
			return null;
		}
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
	

@SuppressWarnings("unchecked")
public List<GrantedAuthority> extractRoles(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();

    List<String> roles = claims.get("roles", List.class);
    return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
}


    public boolean isTokenValid(String token, String userName) {
        return extractUsername(token).equals(userName);
    }
    
    
    public Claims getUserPrincipleFromJWT(String token) {
    	log.info("Entered into getUserPrincipleFromJWT method");
    	
    	Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        log.info("Exit from getUserPrincipleFromJWT method=====");
        
        return claims;
    }
    
    public  UserDetails convertToUserDetails(User user) {
    	return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(), 
    			List.of(new SimpleGrantedAuthority(user.getRole().name())));
    }
    
    public String getJwtFromRequest(HttpServletRequest request) {
    	String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }else {
        	return null;
        }
    }
}