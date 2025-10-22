package com.education.lending.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DashboardController {
	 @GetMapping("admin/dashboard")
	    public String adminDashboard() {
	        return "Welcome Admin!";
	    }
	 @GetMapping("user/dashboard")
	    public String userDashboard() {
	        return "Welcome User!";
	    }
}
