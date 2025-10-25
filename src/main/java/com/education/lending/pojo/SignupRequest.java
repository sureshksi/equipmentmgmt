package com.education.lending.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SignupRequest {
	private String name;
    private String email;
    private String password;
    private String mobile;
    private String loginId;
    private String role;
}
