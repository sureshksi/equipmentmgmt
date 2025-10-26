package com.education.lending.pojo;

import org.springframework.security.core.userdetails.UserDetails;

import com.education.lending.entity.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    private String token;
    private String userName;
    private String role;
    private String name;
    private String email;

    public AuthResponse(String token, User user) {
    	this.token = token;
        this.userName=user.getLoginId();
        this.role=user.getRole().name();
        this.name=user.getName();
        this.email=user.getEmail();
    }
    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String token, UserDetails userDetails) {
        this.token = token;
        this.userName=userDetails.getUsername();
//        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
//        this.roles = roles.stream().map(GrantedAuthority::getAuthority).toList();
    }
}
