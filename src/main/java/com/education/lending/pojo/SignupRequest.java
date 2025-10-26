package com.education.lending.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SignupRequest {
	private String name;
    private String email;
    @JsonIgnore
    private String password;
    private String mobile;
    private String loginId;
    private String role;
    
    public String toJson(SignupRequest singupRequest) {
    	ObjectMapper mapper = new ObjectMapper();
    	String json="";
		try {
			json = mapper.writeValueAsString(singupRequest);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return json;
    }
}
